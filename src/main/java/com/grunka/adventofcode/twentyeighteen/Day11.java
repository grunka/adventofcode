package com.grunka.adventofcode.twentyeighteen;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11 {
    private static final int GRID_SERIAL_NUMBER = 9005;

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static class PowerGridView {
        private final int x;
        private final int y;
        private final int size;
        private final int level;

        PowerGridView(int size) {
            this.x = 0;
            this.y = 0;
            this.size = size;
            this.level = powerLevelGrid(x, y, size);
        }

        PowerGridView(int x, int y, int size, int level) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.level = level;
        }

        boolean hasRight() {
            return x + size < 300;
        }

        boolean hasDown() {
            return y + size < 300;
        }

        PowerGridView moveRight() {
            int left = 0;
            int right = 0;
            for (int i = 0; i < size; i++) {
                left += powerLevel(x, y + i);
                right += powerLevel(x + size, y + i);
            }
            return new PowerGridView(x + 1, y, size, level - left + right);
        }

        PowerGridView moveDown() {
            int top = 0;
            int bottom = 0;
            for (int i = 0; i < size; i++) {
                top += powerLevel(x + i, y);
                bottom += powerLevel(x + i, y + size);
            }
            return new PowerGridView(x, y + 1, size, level - top + bottom);
        }

        String toCoordinate() {
            return (x + 1) + "," + (y + 1);
        }

        String toCoordinateAndSize() {
            return (x + 1) + "," + (y + 1) + "," + size;
        }
    }

    private static void part2() {
        String maxCoordinateAndSize = IntStream.range(1, 301)
                .parallel()
                .mapToObj(size -> Stream.iterate(new PowerGridView(size), PowerGridView::hasDown, PowerGridView::moveDown)).flatMap(stream -> stream)
                .map(view -> Stream.iterate(view, PowerGridView::hasRight, PowerGridView::moveRight)).flatMap(stream -> stream)
                .reduce((a, b) -> a.level > b.level ? a : b)
                .map(PowerGridView::toCoordinateAndSize)
                .orElseThrow();
        System.out.println("Part 2 result: " + maxCoordinateAndSize);
    }

    private static void part1() {
        String maxCoordinate = Stream.iterate(new PowerGridView(3), PowerGridView::hasDown, PowerGridView::moveDown)
                .map(view -> Stream.iterate(view, PowerGridView::hasRight, PowerGridView::moveRight)).flatMap(stream -> stream)
                .reduce((a, b) -> a.level > b.level ? a : b)
                .map(PowerGridView::toCoordinate)
                .orElseThrow();
        System.out.println("Part 1 result: " + maxCoordinate);
    }

    private static int powerLevelGrid(int x, int y, int size) {
        int sum = 0;
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                sum += powerLevel(x + a, y + b);
            }
        }
        return sum;
    }

    private static int powerLevel(int x, int y) {
        x++;
        y++;
        int rackId = x + 10;
        int powerLevel = rackId * y;
        powerLevel = powerLevel + Day11.GRID_SERIAL_NUMBER;
        powerLevel = powerLevel * rackId;
        int hundredsDigit = (powerLevel / 100) % 10;
        return hundredsDigit - 5;
    }
}
