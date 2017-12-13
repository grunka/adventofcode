package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day13 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        System.out.println("Part 1");
        int[][] firewall = parseFirewall(INPUT);
        int severity = calculateSeverity(firewall);
        System.out.println("severity = " + severity);
        System.out.println();
    }

    private static void part2() {
        System.out.println("Part 2");
        int[][] firewall = parseFirewall(INPUT);
        int delay = 0;
        while (wasCaught(firewall)) {
            for (int[] layer : firewall) {
                if (layer != null) {
                    rotate(layer);
                }
            }
            delay++;
        }
        System.out.println("delay = " + delay);
        System.out.println();
    }

    private static boolean wasCaught(int[][] firewall) {
        for (int[] layer : firewall) {
            if (layer != null && layer[0] == 0) {
                return true;
            }
        }
        return false;
    }

    private static int calculateSeverity(int[][] firewall) {
        int severity = 0;
        for (int i = 0; i < firewall.length; i++) {
            if (firewall[i] != null && firewall[i][0] == 0) {
                // caught
                severity += i * (firewall[i].length / 2 + 1);
            }
        }
        return severity;
    }

    private static int[][] parseFirewall(String input) {
        int[][] firewall = new int[0][];
        for (String line : input.split("\n")) {
            String[] depthAndRange = line.split(": ");
            int depth = Integer.parseInt(depthAndRange[0]);
            int range = Integer.parseInt(depthAndRange[1]);
            firewall = Arrays.copyOf(firewall, depth + 1);
            firewall[depth] = IntStream.concat(IntStream.range(0, range), IntStream.range(1, range - 1).map(i -> range - i - 1)).toArray();
            for (int i = 0; i < depth; i++) {
                rotate(firewall[depth]);
            }
        }
        return firewall;
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
