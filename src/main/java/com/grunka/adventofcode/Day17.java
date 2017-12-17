package com.grunka.adventofcode;

import java.util.Arrays;

public class Day17 {
    public static void main(String[] args) {
        int[] buffer = new int[1];
        int position = 0;
        int steps = 345;

        for (int i = 0; i < 2017; i++) {
            position = (position + steps) % buffer.length;
            buffer = Arrays.copyOf(buffer, buffer.length + 1);
            System.arraycopy(buffer, position, buffer, position + 1, buffer.length - position - 1);
            position++;
            buffer[position] = i + 1;
            //System.out.println("buffer = " + Arrays.toString(buffer));
        }
        System.out.println("buffer[position] = " + buffer[position]);
        System.out.println("buffer[position + 1] = " + buffer[position + 1]);
    }
}
