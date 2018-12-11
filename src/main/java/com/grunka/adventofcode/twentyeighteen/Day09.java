package com.grunka.adventofcode.twentyeighteen;

import java.util.Arrays;

public class Day09 {
    public static void main(String[] args) {
        System.out.println("Part 1 result: " + Arrays.stream(calculateScores(468, 71843)).max().orElseThrow());
        System.out.println("Part 2 result: " + Arrays.stream(calculateScores(468, 7184300)).max().orElseThrow());
    }

    private static long[] calculateScores(int players, int maxMarble) {
        Circle first = Circle.create();
        Circle current = first;
        //System.out.println(first);
        long[] scores = new long[players];
        int player = 0;
        int marble = 1;
        while (marble <= maxMarble) {
            if (marble % 23 == 0) {
                scores[player] += marble;
                Circle extra = current.counterClockwise.counterClockwise.counterClockwise.counterClockwise.counterClockwise.counterClockwise.counterClockwise;
                scores[player] += extra.marble;
                current = extra.remove();
            } else {
                current = current.clockwise.insertAfter(marble);
            }
            marble++;
            player = (player + 1) % players;
            //System.out.println(first);
        }
        //System.out.println("scores = " + Arrays.toString(scores));
        return scores;
    }

    private static class Circle {
        boolean start;
        int marble;
        Circle clockwise;
        Circle counterClockwise;

        static Circle create() {
            Circle c = new Circle();
            c.start = true;
            c.marble = 0;
            c.clockwise = c;
            c.counterClockwise = c;
            return c;
        }

        Circle insertAfter(int marble) {
            Circle c = new Circle();
            c.marble = marble;
            c.counterClockwise = this;
            c.clockwise = this.clockwise;
            this.clockwise.counterClockwise = c;
            this.clockwise = c;
            return c;
        }

        @Override
        public String toString() {
            String result = this.marble + " ";
            Circle current = this.clockwise;
            while (current != this) {
                result += String.format("% 3d ", current.marble);
                current = current.clockwise;
            }
            return result.trim();
        }

        Circle remove() {
            counterClockwise.clockwise = clockwise;
            clockwise.counterClockwise = counterClockwise;
            return clockwise;
        }
    }
}
