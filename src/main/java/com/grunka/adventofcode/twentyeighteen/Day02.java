package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day02 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(Day02.class.getResource("/twentyeighteen/Day02-1.txt").toURI()));
        int twos = lines.stream().filter(s -> hasExactCount(2, s)).mapToInt(s -> 1).sum();
        int threes = lines.stream().filter(s -> hasExactCount(3, s)).mapToInt(s -> 1).sum();
        int result = twos * threes;
        System.out.println("Part 1 result: " + result);
    }

    private static boolean hasExactCount(int count, String input) {
        Map<String, Integer> counter = Arrays.stream(input.split("")).collect(Collectors.toMap(s -> s, s -> 1, (a, b) -> a + b));
        return counter.entrySet().stream().anyMatch(e -> e.getValue() == count);
    }
}
