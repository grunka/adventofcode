package com.grunka.adventofcode.twentyseventeen;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) {
        String input = "ugkiagan";
        part1(input);
        part2(input);
    }

    private static void part2(String input) {
        System.out.println("Part 2");
        int[][] grid = new int[128][];
        AtomicInteger row = new AtomicInteger();
        Consumer<String> lineEater = line -> grid[row.getAndIncrement()] = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
        generateGrid(input, lineEater);

        int groups = 0;
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                if (grid[x][y] == 1) {
                    groups++;
                    floodFill(grid, x, y);
                }
            }
        }
        System.out.println("groups = " + groups);
        System.out.println();
    }

    private static void floodFill(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x > 127 || y > 127 || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        floodFill(grid, x - 1, y);
        floodFill(grid, x + 1, y);
        floodFill(grid, x, y - 1);
        floodFill(grid, x, y + 1);
    }

    private static void part1(String input) {
        System.out.println("Part 1");
        AtomicInteger total = new AtomicInteger();
        Consumer<String> lineEater = line -> total.addAndGet(Arrays.stream(line.split("")).mapToInt(Integer::parseInt).sum());
        generateGrid(input, lineEater);
        System.out.println("total = " + total);
        System.out.println();
    }

    private static void generateGrid(String input, Consumer<String> lineEater) {
        for (int i = 0; i < 128; i++) {
            String hash = Day10.knotHash(input + "-" + i);
            String line = Arrays.stream(hash.split(""))
                    .map(hex -> Integer.parseInt(hex, 16))
                    .map(n -> String.format("%4s", Integer.toBinaryString(n)).replace(' ', '0'))
                    .collect(Collectors.joining());
            lineEater.accept(line);
        }
    }
}
