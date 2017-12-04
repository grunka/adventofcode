package com.grunka.adventofcode;

import java.util.function.BiFunction;

public class Day3 {
    private enum Direction {
        RIGHT,
        UP,
        LEFT,
        DOWN
    }

    private static BiFunction<Integer, Integer, Integer> MANHATTAN_DISTANCE = (x, y) -> Math.abs(x) + Math.abs(y);

    public static void main(String[] args) {
        System.out.println("Distance for 1:      " + calculatePosition(1, MANHATTAN_DISTANCE));
        System.out.println("Distance for 12:     " + calculatePosition(12, MANHATTAN_DISTANCE));
        System.out.println("Distance for 23:     " + calculatePosition(23, MANHATTAN_DISTANCE));
        System.out.println("Distance for 1024:   " + calculatePosition(1024, MANHATTAN_DISTANCE));
        System.out.println("Distance for 361527: " + calculatePosition(361527, MANHATTAN_DISTANCE));
    }

    private static <T> T calculatePosition(int n, BiFunction<Integer, Integer, T> result) {
        int x = 0;
        int y = 0;
        int xMax = 0;
        int yMax = 0;
        int xMin = 0;
        int yMin = 0;
        Direction direction = Direction.RIGHT;

        for (int i = 1; i < n; i++) {
            switch (direction) {
                case RIGHT:
                    x++;
                    if (x > xMax) {
                        direction = Direction.UP;
                        xMax = x;
                    }
                    break;
                case UP:
                    y++;
                    if (y > yMax) {
                        direction = Direction.LEFT;
                        yMax = y;
                    }
                    break;
                case LEFT:
                    x--;
                    if (x < xMin) {
                        direction = Direction.DOWN;
                        xMin = x;
                    }
                    break;
                case DOWN:
                    y--;
                    if (y < yMin) {
                        direction = Direction.RIGHT;
                        yMin = y;
                    }
                    break;
            }
        }
        return result.apply(x, y);
    }
}
