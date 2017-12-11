package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        System.out.println("Part 1");
        int[] list = IntStream.range(0, 256).toArray();
        int[] lengths = Arrays.stream(INPUT.split(",")).mapToInt(Integer::parseInt).toArray();
        hash(list, lengths, 1);
        System.out.println("list = " + Arrays.toString(list));
        int result = list[0] * list[1];
        System.out.println("result = " + result);
    }

    private static void part2() {
        System.out.println("Part 2");
        int[] list = IntStream.range(0, 256).toArray();
        int[] lengths = IntStream.concat(
                Arrays.stream(INPUT.split("")).filter(s -> !s.isEmpty()).mapToInt(s -> s.charAt(0)),
                IntStream.of(17, 31, 73, 47, 23)
        ).toArray();
        System.out.println("lengths = " + Arrays.toString(lengths));
        hash(list, lengths, 64);
        System.out.println("list = " + Arrays.toString(list));
        int[] blocks = new int[16];
        for (int block = 0; block < 16; block++) {
            blocks[block] = list[block * 16];
            for (int element = 1; element < 16; element++) {
                blocks[block] ^= list[block * 16 + element];
            }
        }
        System.out.println("blocks = " + Arrays.toString(blocks));
        String result = Arrays.stream(blocks).mapToObj(Integer::toHexString).collect(Collectors.joining());
        System.out.println("result = " + result);
    }

    private static void hash(int[] list, int[] lengths, int rounds) {
        int position = 0;
        int skip = 0;
        for (int round = 0; round < rounds; round++) {
            for (int length : lengths) {
                reverse(list, position, length);
                position = (position + length + skip) % list.length;
                skip++;
            }
        }
    }

    private static void reverse(int[] list, int position, int length) {
        for (int i = 0; i < length / 2; i++) {
            swap(list, position + i, position + length - i - 1);
        }
    }

    private static void swap(int[] list, int i, int j) {
        int t = list[i % list.length];
        list[i % list.length] = list[j % list.length];
        list[j % list.length] = t;
    }

    private static final String INPUT = "76,1,88,148,166,217,130,0,128,254,16,2,130,71,255,229";
}
