package com.grunka.adventofcode.twentyseventeen;

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
        test("a2582a3a0e66e6e86e3812dcb672a272", knotHash(""));
        test("33efeb34ea91902bb2f59c9920caa6cd", knotHash("AoC 2017"));
        test("3efbe78a8d82f29979031a4aa0b16a9d", knotHash("1,2,3"));
        test("63960835bcdc130f0b66d7ff4f6a5a8e", knotHash("1,2,4"));
        String result = knotHash(INPUT);
        System.out.println("result = " + result);
    }

    private static void test(String expected, String result) {
        if (!result.equals(expected)) {
            throw new IllegalStateException("Got " + result + " when expecting " + expected);
        }
    }

    public static String knotHash(String input) {
        int[] list = IntStream.range(0, 256).toArray();
        int[] lengths = IntStream.concat(
                Arrays.stream(input.split("")).filter(s -> !s.isEmpty()).mapToInt(s -> s.charAt(0)),
                IntStream.of(17, 31, 73, 47, 23)
        ).toArray();
        //System.out.println("lengths = " + Arrays.toString(lengths));
        hash(list, lengths, 64);
        //System.out.println("list = " + Arrays.toString(list));
        int[] blocks = new int[16];
        for (int block = 0; block < 16; block++) {
            blocks[block] = list[block * 16];
            for (int element = 1; element < 16; element++) {
                blocks[block] ^= list[block * 16 + element];
            }
        }
        //System.out.println("blocks = " + Arrays.toString(blocks));
        return Arrays.stream(blocks).mapToObj(i -> String.format("%02x", i)).collect(Collectors.joining());
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
