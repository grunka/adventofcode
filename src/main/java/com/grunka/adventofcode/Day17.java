package com.grunka.adventofcode;

public class Day17 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part2() {
        System.out.println("Part 2");
        Buffer buffer = doInserts(50_000_000);
        Buffer zero = buffer.find(0);
        System.out.println("zero.next.value = " + zero.next.value);
    }

    private static void part1() {
        System.out.println("Part 1");
        Buffer buffer = doInserts(2017);
        System.out.println("buffer.next.value = " + buffer.next.value);
    }

    private static Buffer doInserts(int n) {
        int steps = 345;
        Buffer buffer = new Buffer(0);
        for (int i = 0; i < n; i++) {
            buffer = buffer.step(steps);
            buffer = buffer.insert(i + 1);
            if (i % 100_000 == 0) {
                System.out.println("i = " + i);
            }
        }
        return buffer;
    }

    private static class Buffer {
        static int size;
        Buffer next;
        final int value;

        Buffer(int value) {
            this.value = value;
            this.next = this;
            size = 1;
        }

        Buffer(int value, Buffer next) {
            this.value = value;
            this.next = next;
        }

        Buffer insert(int value) {
            next = new Buffer(value, next);
            size++;
            return next;
        }

        Buffer find(int value) {
            Buffer current = this;
            while (current.value != value) {
                current = current.next;
            }
            return current;
        }

        Buffer step(int steps) {
            Buffer current = this;
            for (int i = 0; i < steps; i++) {
                current = current.next;
            }
            return current;
        }
    }
}
