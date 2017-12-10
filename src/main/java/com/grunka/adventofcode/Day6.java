package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
    private static final String INPUT = "4\t1\t15\t12\t0\t9\t9\t5\t5\t8\t7\t3\t14\t5\t12\t3";
    private static final String TEST_INPUT = "0\t2\t7\t0";

    public static void main(String[] args) {
        part1(Arrays.stream(INPUT.split("\t")).mapToInt(Integer::parseInt).toArray());
        part2(Arrays.stream(INPUT.split("\t")).mapToInt(Integer::parseInt).toArray());
    }

    private static void part2(int[] memory) {
        System.out.println("Part 2");
        redistribute(memory);
        Set<String> redistributed = redistribute(memory);
        System.out.println("loop size = " + redistributed.size());
    }

    private static void part1(int[] memory) {
        System.out.println("Part 1");
        Set<String> seen = redistribute(memory);
        System.out.println(seen.size());
    }

    private static Set<String> redistribute(int[] memory) {
        Set<String> seen = new HashSet<>();
        while (seen.add(Arrays.toString(memory))) {
            int largestIndex = 0;
            int largestValue = memory[0];
            for (int i = 0; i < memory.length; i++) {
                if (memory[i] > largestValue) {
                    largestValue = memory[i];
                    largestIndex = i;
                }
            }
            memory[largestIndex] = 0;
            int startingIndex = largestIndex + 1;
            for (int i = startingIndex; i < largestValue + startingIndex; i++) {
                memory[i % memory.length]++;
            }
            //System.out.println("memory = " + Arrays.toString(memory));
        }
        return seen;
    }
}
