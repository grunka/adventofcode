package com.grunka.adventofcode.twentyeighteen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 {
    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    private static void part1() throws IOException {
        int sum = getValues().stream().mapToInt(i -> i).sum();
        System.out.println("Part 1 result: " + sum);
    }

    private static void part2() throws IOException {
        List<Integer> values = getValues();
        Set<Integer> seen = new HashSet<>();
        int result = 0;
        int i = 0;
        boolean found = false;
        while (!found) {
            int value = values.get(i);
            i = (i + 1) % values.size();
            result += value;
            found = !seen.add(result);
        }
        System.out.println("Part 2 result: " + result);
    }

    private static List<Integer> getValues() throws IOException {
        List<Integer> values = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day01.class.getResourceAsStream("/twentyeighteen/Day01-1.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean add = line.charAt(0) == '+';
                int value = Integer.parseInt(line.substring(1));
                if (add) {
                    values.add(value);
                } else {
                    values.add(-value);
                }
            }
        }
        return values;
    }
}
