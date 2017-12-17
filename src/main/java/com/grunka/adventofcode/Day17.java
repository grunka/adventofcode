package com.grunka.adventofcode;

public class Day17 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part2() {
        System.out.println("Part 2");
        Buffer buffer = doInserts(50_000_000);
        Buffer zero = buffer.findZero();
        System.out.println("zero.next.value = " + zero.next.value);
    }

    private static void part1() {
        System.out.println("Part 1");
        Buffer buffer = doInserts(2017);
        System.out.println("buffer.next.value = " + buffer.next.value);
    }

    private static Buffer doInserts(int n) {
        Buffer buffer = new Buffer(0);
        for (int i = 0; i < n; i++) {
            buffer = buffer.step().insert(i + 1);
            if (i % 1_000_000 == 0) {
                System.out.println("i = " + i);
            }
        }
        return buffer;
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
