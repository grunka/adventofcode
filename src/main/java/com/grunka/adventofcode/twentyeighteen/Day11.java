package com.grunka.adventofcode.twentyeighteen;

public class Day11 {
    public static void main(String[] args) {
        if (4 != powerLevel(8, 3, 5)) {
            throw new Error("Incorrect power level");
        }
        int maxX = -1;
        int maxY = -1;
        int max = -1;
        int gridSerialNumber = 9005;
        for (int y = 1; y <= 300 - 3; y++) {
            for (int x = 1; x <= 300 - 3; x++) {
                int powerLevel = powerLevelGrid(gridSerialNumber, x, y);
                if (powerLevel > max) {
                    max = powerLevel;
                    maxX = x;
                    maxY = y;
                }
            }
        }
        System.out.println("Part 1 result: " + maxX + "," + maxY);
    }

    private static int powerLevelGrid(int gridSerialNumber, int x, int y) {
        return powerLevel(gridSerialNumber, x, y) +
                powerLevel(gridSerialNumber, x + 1, y) +
                powerLevel(gridSerialNumber, x + 2, y) +
                powerLevel(gridSerialNumber, x, y + 1) +
                powerLevel(gridSerialNumber, x + 1, y + 1) +
                powerLevel(gridSerialNumber, x + 2, y + 1) +
                powerLevel(gridSerialNumber, x, y + 2) +
                powerLevel(gridSerialNumber, x + 1, y + 2) +
                powerLevel(gridSerialNumber, x + 2, y + 2);
    }

    private static int powerLevel(int gridSerialNumber, int x, int y) {
        int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel = powerLevel + gridSerialNumber;
        powerLevel = powerLevel * rackId;
        int hundredsDigit = (powerLevel / 100) % 10;
        return hundredsDigit - 5;
    }
}
