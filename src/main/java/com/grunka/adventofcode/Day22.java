package com.grunka.adventofcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.grunka.adventofcode.Day22.Direction.UP;

public class Day22 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        System.out.println("Part 1");
        run(INPUT, false, 10000);
    }

    private static void part2() {
        System.out.println("Part 2");
        run(INPUT, true, 10000000);
    }

    private static void run(String input, boolean extended, int bursts) {
        int columns = input.indexOf('\n');
        int middleHorizontal = columns / 2;
        int rows = countRows(input);
        int middleVertical = rows / 2;
        Map<Position, Character> nodes = new HashMap<>();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                nodes.put(
                        new Position(column - middleHorizontal, row - middleVertical),
                        input.charAt(column + row * (columns + 1))
                );
            }
        }
        Position position = new Position(0, 0);
        Direction direction = UP;
        int infections = 0;
        for (int i = 0; i < bursts; i++) {
            char state = get(position, nodes);
            switch (state) {
                case '#':
                    direction = direction.turnRight();
                    nodes.put(position, extended ? 'F' : '.');
                    break;
                case '.':
                    direction = direction.turnLeft();
                    nodes.put(position, extended ? 'W' : '#');
                    if (!extended) {
                        infections++;
                    }
                    break;
                case 'F':
                    direction = direction.reverse();
                    nodes.put(position, '.');
                    break;
                case 'W':
                    nodes.put(position, '#');
                    infections++;
                    break;
            }
            position = position.move(direction);
            //System.out.println(nodes);
            //System.out.println();
        }
        System.out.println("infections = " + infections);
    }

    private static char get(Position position, Map<Position, Character> nodes) {
        return nodes.computeIfAbsent(position, p -> '.');
    }

    private static class Position {
        final int x;
        final int y;

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Position move(Direction direction) {
            switch (direction) {
                case UP:
                    return new Position(x, y - 1);
                case DOWN:
                    return new Position(x, y + 1);
                case LEFT:
                    return new Position(x - 1, y);
                case RIGHT:
                    return new Position(x + 1, y);
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        Direction turnLeft() {
            return getDirection(LEFT, RIGHT, DOWN, UP);
        }

        Direction turnRight() {
            return getDirection(RIGHT, LEFT, UP, DOWN);
        }

        Direction reverse() {
            return getDirection(DOWN, UP, RIGHT, LEFT);
        }

        private Direction getDirection(Direction up, Direction down, Direction left, Direction right) {
            switch (this) {
                case UP:
                    return up;
                case DOWN:
                    return down;
                case LEFT:
                    return left;
                case RIGHT:
                    return right;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private static int countRows(String input) {
        int index = -1;
        int count = 0;
        while ((index = input.indexOf('\n', index + 1)) != -1) {
            count++;
        }
        return count + 1;
    }

    private static final String TEST_INPUT =
            "..#\n" +
                    "#..\n" +
                    "...";

    private static final String INPUT =
            "###.######..##.##..#..#.#\n" +
                    "#.#.#.##.##.#####..##..#.\n" +
                    "##...#.....#.#.#..##.#.##\n" +
                    "....#####.#.#.#..###.###.\n" +
                    "###.#.......#..#.#...#..#\n" +
                    ".#.######.##.#.....#...##\n" +
                    "##.#...#..#..#....##.#.#.\n" +
                    "#.##..#..##.##..###...#.#\n" +
                    ".#.......#.#..####.#.#.##\n" +
                    ".#...###...##..#...#.#..#\n" +
                    "...##......#.##.....#..#.\n" +
                    "######....##...##.....#.#\n" +
                    ".####..##..##.#.##.##..#.\n" +
                    ".#.#...###.#....#.##.####\n" +
                    "..####..#.#..#.#.#......#\n" +
                    "#.#..##..#####.#.#....##.\n" +
                    ".....#..########....#.##.\n" +
                    "##.###....#..###..#.....#\n" +
                    ".#.##...#.#...###.##...#.\n" +
                    "..#.##..#..####.##..###.#\n" +
                    ".#..#.##..#.##...#####.#.\n" +
                    "#..##............#..#....\n" +
                    "###.....#.##.#####...#.##\n" +
                    "##.##..#.....##..........\n" +
                    "#.#..##.#.#..#....##..#.#";
}
