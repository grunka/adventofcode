package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day07 {
    private static final Pattern LINE = Pattern.compile("^Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.$");

    public static void main(String[] args) throws URISyntaxException, IOException {
        part1();
    }

    private static void part1() throws IOException, URISyntaxException {
        Map<String, List<String>> prerequisites = getPrerequisites();
        System.out.print("Part 1 result: ");
        while (!prerequisites.isEmpty()) {
            String next = prerequisites.entrySet().stream().filter(e -> e.getValue().isEmpty()).map(Map.Entry::getKey).sorted().findFirst().orElseThrow();
            System.out.print(next);
            prerequisites.remove(next);
            prerequisites.values().forEach(l -> l.remove(next));
        }
        System.out.println();
    }

    private static Map<String, List<String>> getPrerequisites() throws IOException, URISyntaxException {
        Map<String, List<String>> prerequisites = Files.readAllLines(Paths.get(Day07.class.getResource("/twentyeighteen/Day07-1.txt").toURI())).stream()
                //.peek(System.out::println)
                .map(LINE::matcher)
                .peek(m -> {
                    if (!m.matches()) {
                        throw new Error("Unmatched line");
                    }
                })
                .collect(Collectors.toMap(
                        m -> m.group(2),
                        m -> new ArrayList<>(List.of(m.group(1))),
                        (a, b) -> Stream.of(a, b)
                                .flatMap(Collection::stream)
                                .sorted()
                                .distinct()
                                .collect(Collectors.toCollection(ArrayList::new))
                        )
                );
        prerequisites.values().stream().flatMap(Collection::stream).sorted().distinct().filter(n -> !prerequisites.keySet().contains(n)).forEach(n -> prerequisites.put(n, new ArrayList<>()));
        return prerequisites;
    }
}
