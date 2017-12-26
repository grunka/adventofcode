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


        run(INPUT, false, 10000);
        run(INPUT, true, 10000000);
    }

    private static void run(String nodes, boolean extended, int bursts) {

        Position position = new Position(countColumns(nodes) / 2, countRows(nodes) / 2);
        Direction direction = UP;
        int infections = 0;
        for (int i = 0; i < bursts; i++) {
            char state = get(position, nodes);
            switch (state) {
                case '#':
                    direction = direction.turnRight();
                    nodes = set(position, extended ? 'F' : '.', nodes);
                    break;
                case '.':
                    direction = direction.turnLeft();
                    nodes = set(position, extended ? 'W' : '#', nodes);
                    if (!extended) {
                        infections++;
                    }
                    break;
                case 'F':
                    direction = direction.reverse();
                    nodes = set(position, '.', nodes);
                    break;
                case 'W':
                    nodes = set(position, '#', nodes);
                    infections++;
                    break;
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
