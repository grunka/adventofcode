package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day07 {
    private static final Pattern LINE = Pattern.compile("^Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.$");

    public static void main(String[] args) throws URISyntaxException, IOException {
        part1();
        part2();
    }

    private static void part2() throws URISyntaxException, IOException {
        Map<String, List<String>> prerequisites = getPrerequisites();
        Map<String, Integer> activeWork = new TreeMap<>();
        final int workers = 5;
        final int extraWork = 60;
        long second = 0;
        List<String> completed = new ArrayList<>();
        while (!prerequisites.isEmpty() || !activeWork.isEmpty()) {
            List<String> finishedWork = activeWork.entrySet().stream().filter(e -> e.getValue() == 0).map(Map.Entry::getKey).collect(Collectors.toList());
            finishedWork.forEach(finished -> {
                completed.add(finished);
                activeWork.remove(finished);
                prerequisites.remove(finished);
                prerequisites.values().forEach(l -> l.remove(finished));
            });

            List<String> queue = prerequisites.entrySet().stream().filter(e -> e.getValue().isEmpty()).map(Map.Entry::getKey).filter(w -> !activeWork.containsKey(w)).sorted().collect(Collectors.toList());
            queue.subList(0, Math.min(workers - activeWork.size(), queue.size())).forEach(work -> activeWork.put(work, extraWork + toWorkTime(work)));

            activeWork.entrySet().forEach(e -> e.setValue(e.getValue() - 1));

            System.out.println(String.format("% 4d %s", second, String.join(" ", activeWork.keySet())));
            second++;
        }
        System.out.println("Part 2 result: " + String.join("", completed));
    }

    private static int toWorkTime(String work) {
        return work.charAt(0) - 'A' + 1;
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
