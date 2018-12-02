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
        part1();
        part2();
    }

    private static void part2() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(Day02.class.getResource("/twentyeighteen/Day02-1.txt").toURI()));
        while (lines.size() > 1) {
            String candidate = lines.get(0);
            lines = lines.subList(1, lines.size());
            lines.forEach(line -> {
                int differences = 0;
                char lastDifferent = 0;
                for (int i = 0; i < candidate.length(); i++) {
                    if (line.charAt(i) != candidate.charAt(i)) {
                        differences++;
                        lastDifferent = candidate.charAt(i);
                    }
                }
                if (differences == 1) {
                    char different = lastDifferent;
                    String common = Arrays.stream(candidate.split("")).filter(s -> s.charAt(0) != different).collect(Collectors.joining());
                    System.out.println("Part 2 result: " + common);
                }
            });
        }
    }

    private static void part1() throws IOException, URISyntaxException {
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
