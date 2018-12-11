package com.grunka.adventofcode.twentyeighteen;

public class Day11 {
    private static final int GRID_SERIAL_NUMBER = 9005;
    private static final int[][] GRID = new int[300][300];

    public static void main(String[] args) {
        fillGrid();
        part1();
        part2();
    }

    private static void fillGrid() {
        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                GRID[x][y] = powerLevel(x + 1, y + 1);
            }
        }
    }

    private static void part2() {
        int maxX = -1;
        int maxY = -1;
        int maxSize = -1;
        int max = -1;
        for (int size = 1; size <= 300; size++) {
            for (int y = 1; y <= 300 - size; y++) {
                for (int x = 1; x <= 300 - size; x++) {
                    int powerLevel = powerLevelGrid(x, y, size);
                    if (powerLevel > max) {
                        max = powerLevel;
                        maxX = x;
                        maxY = y;
                        maxSize = size;
                    }
                }
            }
        }
        System.out.println("Part 2 result: " + maxX + "," + maxY + "," + maxSize);
    }

    private static void part1() {
        int maxX = -1;
        int maxY = -1;
        int max = -1;
        for (int y = 1; y <= 300 - 3; y++) {
            for (int x = 1; x <= 300 - 3; x++) {
                int powerLevel = powerLevelGrid(x, y, 3);
                if (powerLevel > max) {
                    max = powerLevel;
                    maxX = x;
                    maxY = y;
                }
            }
        }
        System.out.println("Part 1 result: " + maxX + "," + maxY);
    }

    private static int powerLevelGrid(int x, int y, int size) {
        int sum = 0;
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                sum += GRID[x + a - 1][y + b - 1];
            }
        }
        return sum;
    }

    private static int powerLevel(int x, int y) {
        int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel = powerLevel + Day11.GRID_SERIAL_NUMBER;
        powerLevel = powerLevel * rackId;
        int hundredsDigit = (powerLevel / 100) % 10;
        return hundredsDigit - 5;
    }
}
