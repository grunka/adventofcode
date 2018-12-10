package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        part1();
        part2();
    }

    private static void part2() throws URISyntaxException, IOException {
        String baseInput = Files.readString(Paths.get(Day04.class.getResource("/twentyeighteen/Day05-1.txt").toURI())).trim();
        Integer smallest = Arrays.stream(baseInput.split(""))
                .map(String::toUpperCase)
                .distinct()
                .map(c -> baseInput.replaceAll("[" + c + c.toLowerCase() + "]", ""))
                .map(Day05::reduce)
                .reduce((a, b) -> a < b ? a : b)
                .orElseThrow();

        System.out.println("Part 2 result: " + smallest);
    }

    private static void part1() throws IOException, URISyntaxException {
        String input = Files.readString(Paths.get(Day04.class.getResource("/twentyeighteen/Day05-1.txt").toURI())).trim();
        System.out.println("Part 1 result: " + reduce(input));
    }

    private static int reduce(String input) {
        List<String> c = new ArrayList<>(Arrays.asList(input.split("")));
        for (int i = 1; i < c.size(); i++) {
            if (reacts(c.get(i - 1), c.get(i))) {
                c.remove(i);
                c.remove(i - 1);
                i = Math.max(i - 2, 0);
            }
        }
        return c.size();
    }

    private static boolean reacts(String a, String b) {
        return a.toUpperCase().equals(b.toUpperCase()) && !a.equals(b);
    }
}
