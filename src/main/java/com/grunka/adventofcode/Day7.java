package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {
    public static void main(String[] args) {
        Arrays.stream(TEST_INPUT.split("\n")).map(line -> {
            String[] split = line.split(" -> ");
            String name;
            List<String> list;
            if (split.length == 1) {
                name = split[0];
                list = Collections.emptyList();
            } else {
                name = split[0];
                list = Arrays.stream(split[1].split(", ")).collect(Collectors.toList());
            }
            return new Program(name, 0, list);
        });
    }

    private static class Program {
        final String name;
        final int weight;
        final List<String> programs;

        private Program(String name, int weight, List<String> programs) {
            this.name = name;
            this.weight = weight;
            this.programs = programs;
        }
    }

    private static final String TEST_INPUT = "pbga (66)\n" +
            "xhth (57)\n" +
            "ebii (61)\n" +
            "havc (66)\n" +
            "ktlj (57)\n" +
            "fwft (72) -> ktlj, cntj, xhth\n" +
            "qoyq (66)\n" +
            "padx (45) -> pbga, havc, qoyq\n" +
            "tknk (41) -> ugml, padx, fwft\n" +
            "jptl (61)\n" +
            "ugml (68) -> gyxo, ebii, jptl\n" +
            "gyxo (61)\n" +
            "cntj (57)";
}
