package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) {
        String input = "flqrgnkx";
        for (int i = 0; i < 128; i++) {
            String hash = Day10.knotHash(input + "-" + i);
            String line = Arrays.stream(hash.split(""))
                    .map(hex -> Integer.parseInt(hex, 16))
                    .map(n -> String.format("%4s", Integer.toBinaryString(n)).replace(' ', '0'))
                    .collect(Collectors.joining());

            System.out.println("line = " + line);
        }
    }
}
