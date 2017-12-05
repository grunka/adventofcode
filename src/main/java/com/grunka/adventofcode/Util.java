package com.grunka.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Util {
    public static String readInput(Class<?> source) {
        String fileName = "/" + source.getSimpleName() + ".txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Util.class.getResourceAsStream(fileName)))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException | NullPointerException e) {
            throw new Error("Could not read " + fileName, e);
        }
    }

    public static int[] toIntArray(String input, String separator) {
        return Arrays.stream(input.split(separator)).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
    }
}
