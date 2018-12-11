package com.grunka.adventofcode.twentyeighteen;

public class Day09 {
    public static void main(String[] args) {
        Circle first = Circle.create();
        Circle current = first;

        System.out.println(first);
        int[] scores = new int[9];
        int player = 0;
        int marble = 1;
        while (marble <= 25) {
            if (marble % 23 == 0) {
                scores[player] += marble;
                Circle extra = current.counterClockwise.counterClockwise.counterClockwise.counterClockwise.counterClockwise.counterClockwise.counterClockwise;
                scores[player] += extra.marble;
                current = extra.remove();
            } else {
                current = current.clockwise.insertAfter(marble);
            }
            marble++;
            player = (player + 1) % scores.length;
            System.out.println(first);
        }
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
