package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day13 {
    public static void main(String[] args) {
        int[][] firewall = new int[0][];
        for (String line : INPUT.split("\n")) {
            String[] depthAndRange = line.split(": ");
            int depth = Integer.parseInt(depthAndRange[0]);
            int range = Integer.parseInt(depthAndRange[1]);
            firewall = Arrays.copyOf(firewall, depth + 1);
            firewall[depth] = IntStream.concat(IntStream.range(0, range), IntStream.range(1, range - 1).map(i -> range - i - 1)).toArray();
        }
        int severity = 0;
        for (int position = 0; position < firewall.length; position++) {
            if (firewall[position] != null && firewall[position][0] == 0) {
                // caught
                severity += position * (firewall[position].length / 2 + 1);
            }
            for (int[] layer : firewall) {
                if (layer != null) {
                    rotate(layer);
                }
            }
        }
        System.out.println("severity = " + severity);
    }

    private static void rotate(int[] values) {
        int a = values[0];
        System.arraycopy(values, 1, values, 0, values.length - 1);
        values[values.length - 1] = a;
    }

    private static final String TEST_INPUT = "0: 3\n" +
            "1: 2\n" +
            "4: 4\n" +
            "6: 4";

    private static final String INPUT = "0: 3\n" +
            "1: 2\n" +
            "2: 5\n" +
            "4: 4\n" +
            "6: 4\n" +
            "8: 6\n" +
            "10: 6\n" +
            "12: 6\n" +
            "14: 8\n" +
            "16: 6\n" +
            "18: 8\n" +
            "20: 8\n" +
            "22: 8\n" +
            "24: 12\n" +
            "26: 8\n" +
            "28: 12\n" +
            "30: 8\n" +
            "32: 12\n" +
            "34: 12\n" +
            "36: 14\n" +
            "38: 10\n" +
            "40: 12\n" +
            "42: 14\n" +
            "44: 10\n" +
            "46: 14\n" +
            "48: 12\n" +
            "50: 14\n" +
            "52: 12\n" +
            "54: 9\n" +
            "56: 14\n" +
            "58: 12\n" +
            "60: 12\n" +
            "64: 14\n" +
            "66: 12\n" +
            "70: 14\n" +
            "76: 20\n" +
            "78: 17\n" +
            "80: 14\n" +
            "84: 14\n" +
            "86: 14\n" +
            "88: 18\n" +
            "90: 20\n" +
            "92: 14\n" +
            "98: 18";
}
