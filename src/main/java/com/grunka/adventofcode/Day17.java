package com.grunka.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Day17 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part2() {
        System.out.println("Part 2");
        List<Integer> buffer = doInserts(50_000_000);
        int position = buffer.indexOf(0);
        System.out.println("position = " + position);
        System.out.println("buffer.get(position + 1) = " + buffer.get(position + 1));
    }

    private static void part1() {
        System.out.println("Part 1");
        List<Integer> buffer = doInserts(2017);
        int position = buffer.indexOf(2017);
        System.out.println("position = " + position);
        System.out.println("buffer.get(position + 1) = " + buffer.get(position + 1));
    }

    private static List<Integer> doInserts(int n) {
        int position = 0;
        int steps = 345;
        List<Integer> buffer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            position = (position + steps) % (i + 1);
            buffer.add(position, i + 1);
            position++;
            if (i % 10_000 == 0) {
                System.out.println("i = " + i);
            }
        }
        return buffer;
    }
}
