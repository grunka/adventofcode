package com.grunka.adventofcode;

public class Day17 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part2() {
        System.out.println("Part 2");
        Buffer buffer = doInserts(50_000_000);
        System.out.println("buffer.find(0).next.value = " + buffer.find(0).next.value);
    }

    private static void part1() {
        System.out.println("Part 1");
        Buffer buffer = doInserts(2017);
        System.out.println("buffer.find(2017).next.value = " + buffer.find(2017).next.value);
    }

    private static Buffer doInserts(int n) {
        Buffer buffer = new Buffer(0, null);
        int position = 0;
        int steps = 345;
        for (int i = 0; i < n; i++) {
            position = (position + steps) % (i + 1);
            buffer.get(position).insert(i + 1);
            position++;
        }
        return buffer;
    }

    private static class Buffer {
        final int value;
        Buffer next;

        Buffer(int value, Buffer next) {
            this.value = value;
            this.next = next;
        }

        void insert(int value) {
            next = new Buffer(value, next);
        }

        Buffer get(int index) {
            Buffer current = this;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        }

        Buffer find(int value) {
            Buffer current = this;
            while (current != null) {
                if (current.value == value) {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }
}
