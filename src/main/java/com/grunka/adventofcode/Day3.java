package com.grunka.adventofcode;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class Day3 {
    private static final int INPUT = 361527;

    private enum Direction {
        RIGHT,
        UP,
        LEFT,
        DOWN
    }

    private static class Point {
        final int x;
        final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {
        part1();
    }

    private static void part1() {
        BiConsumer<Integer, Point> manhattanDistance = (i, p) -> System.out.println("Distance to " + i + ": " + (Math.abs(p.x) + Math.abs(p.y)));
        calculatePosition(1, manhattanDistance);
        calculatePosition(12, manhattanDistance);
        calculatePosition(23, manhattanDistance);
        calculatePosition(1024, manhattanDistance);
        calculatePosition(INPUT, manhattanDistance);
    }

    private static void calculatePosition(int n, BiConsumer<Integer, Point> result) {
        traverse((i, p) -> {
            if (i == n) {
                result.accept(i, p);
                return false;
            } else {
                return true;
            }
        });
    }

    private static void traverse(BiFunction<Integer, Point, Boolean> result) {
        int x = 0;
        int y = 0;
        int xMax = 0;
        int yMax = 0;
        int xMin = 0;
        int yMin = 0;
        Direction direction = Direction.RIGHT;
        boolean shouldContinue;
        int i = 1;
        do {
            shouldContinue = result.apply(i, new Point(x, y));
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
            i++;
        } while (shouldContinue);
    }
}
