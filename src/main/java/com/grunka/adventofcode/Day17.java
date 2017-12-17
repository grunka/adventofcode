package com.grunka.adventofcode;

public class Day17 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part2() {
        System.out.println("Part 2");
        int indexOfZero = 0;
        int position = 0;
        int valueNextToZero = 0;
        int steps = 345;
        for (int i = 0; i < 50_000_000; i++) {
            position = (position + steps) % (i + 1) + 1;
            if (position <= indexOfZero) {
                indexOfZero++;
            }
            if (position == indexOfZero + 1) {
                valueNextToZero = i + 1;
            }
        }
        System.out.println("valueNextToZero = " + valueNextToZero);
    }

    private static void part1() {
        System.out.println("Part 1");
        Buffer buffer = new Buffer(0);
        for (int i = 0; i < 2017; i++) {
            buffer = buffer.step().insert(i + 1);
        }
        System.out.println("buffer.next.value = " + buffer.next.value);
    }

    private static class Buffer {
        Buffer next;
        final int value;

        Buffer(int value) {
            this.value = value;
            this.next = this;
        }

        Buffer(int value, Buffer next) {
            this.value = value;
            this.next = next;
        }

        Buffer insert(int value) {
            next = new Buffer(value, next);
            return next;
        }

        Buffer findZero() {
            Buffer current = this;
            while (current.value != 0) {
                current = current.next;
            }
            return current;
        }

        Buffer step() {
            Buffer current = this;
            for (int i = 0; i < 345; i++) {
                current = current.next;
            }
            return current;
        }
    }
}
