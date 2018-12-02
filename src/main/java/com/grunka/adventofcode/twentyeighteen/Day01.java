package com.grunka.adventofcode.twentyeighteen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day01 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Day01.class.getResourceAsStream("/twentyeighteen/Day01-1.txt")))) {
            String line;
            int result = 0;
            while ((line = reader.readLine()) != null) {
                boolean add = line.charAt(0) == '+';
                int value = Integer.parseInt(line.substring(1));
                if (add) {
                    result += value;
                } else {
                    result -= value;
                }
            }
            System.out.println("result = " + result);
        }
    }
}
