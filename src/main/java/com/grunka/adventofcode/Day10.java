package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day10 {
    public static void main(String[] args) {
        int[] list = IntStream.range(0, 256).toArray();
        int[] lengths = Arrays.stream(INPUT.split(",")).mapToInt(Integer::parseInt).toArray();
        int position = 0;
        int skip = 0;
        for (int length : lengths) {
            reverse(list, position, length);
            position = (position + length + skip) % list.length;
            skip++;
        }
        System.out.println("list = " + Arrays.toString(list));
        int result = list[0] * list[1];
        System.out.println("result = " + result);
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
