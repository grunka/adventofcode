package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.grunka.adventofcode.Day22.Direction.*;

public class Day22 {
    public static void main(String[] args) {
        assert "...\n#..\n...".equals(set(new Position(2, 0), '.', TEST_INPUT));
        assert "..#\n...\n...".equals(set(new Position(0, 1), '.', TEST_INPUT));
        assert ".##\n#..\n...".equals(set(new Position(1, 0), '#', TEST_INPUT));
        assert "..#\n#..\n..#".equals(set(new Position(2, 2), '#', TEST_INPUT));
        assert get(new Position(1, 1), TEST_INPUT) == '.';
        assert get(new Position(0, 1), TEST_INPUT) == '#';


        String nodes = INPUT;
        Position position = new Position(countColumns(nodes) / 2, countRows(nodes) / 2);
        Direction direction = UP;
        int infections = 0;

        for (int i = 0; i < 10000; i++) {
            if (get(position, nodes) == '#') {
                nodes = set(position, '.', nodes);
                direction = direction.turnRight();
            } else {
                infections++;
                nodes = set(position, '#', nodes);
                direction = direction.turnLeft();
            }
            position = position.move(direction);
            if (position.x < 0) {
                nodes = Arrays.stream(nodes.split("\n")).map(line -> "." + line).collect(Collectors.joining("\n"));
                position = position.move(RIGHT);
            } else if (position.y < 0) {
                String row = String.join("", Collections.nCopies(countColumns(nodes), "."));
                nodes = row + "\n" + nodes;
                position = position.move(DOWN);
            } else if (position.x > countColumns(nodes) - 1) {
                nodes = Arrays.stream(nodes.split("\n")).map(line -> line + ".").collect(Collectors.joining("\n"));
            } else if (position.y > countRows(nodes) - 1) {
                String row = String.join("", Collections.nCopies(countColumns(nodes), "."));
                nodes = nodes + "\n" + row;
            }
            //System.out.println(nodes);
            //System.out.println();
        }
        System.out.println("infections = " + infections);
    }

    private static String set(Position position, char c, String nodes) {
        int columns = countColumns(nodes);
        return nodes.substring(0, position.x + position.y * (columns + 1)) + c + nodes.substring(position.x + position.y * (columns + 1) + 1);
    }

    private static char get(Position position, String nodes) {
        return nodes.charAt(position.x + position.y * (countColumns(nodes) + 1));
    }

    private static int countColumns(String nodes) {
        return nodes.indexOf('\n');
    }

    private static class Position {
        final int x;
        final int y;

        private Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position move(Direction direction) {
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
    }

    enum Direction {
        UP, DOWN, LEFT, RIGHT;

        Direction turnLeft() {
            switch (this) {
                case UP:
                    return LEFT;
                case DOWN:
                    return RIGHT;
                case LEFT:
                    return DOWN;
                case RIGHT:
                    return UP;
                default:
                    throw new IllegalStateException();
            }
        }

        Direction turnRight() {
            switch (this) {
                case UP:
                    return RIGHT;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;
                case RIGHT:
                    return DOWN;
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
