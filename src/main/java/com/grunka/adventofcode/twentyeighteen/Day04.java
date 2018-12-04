package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 {
    private static final Pattern LINE = Pattern.compile("^\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] (.*)$");

    public static void main(String[] args) throws URISyntaxException, IOException {
        part1();
    }

    private static void part1() throws IOException, URISyntaxException {
        List<Entry> entries = getEntries();
        Map<Integer, Integer> counts = new TreeMap<>();
        for (int i = 1; i < entries.size(); i++) {
            Entry e = entries.get(i);
            if ("wake".equals(e.action)) {
                int minutes = entries.get(i - 1).time.getMinutes(e.time);
                counts.compute(e.guard, (k, count) -> count == null ? minutes : minutes + count);
            }
        }
        int sleepyGuard = counts.entrySet().stream().reduce((a, b) -> a.getValue() > b.getValue() ? a : b).orElseThrow().getKey();
        List<Entry> sleepyEntries = entries.stream()
                .filter(e -> e.guard == sleepyGuard)
                .filter(e -> "sleep".equals(e.action) || "wake".equals(e.action))
                .collect(Collectors.toList());
        Map<Integer, Integer> minuteCounts = new TreeMap<>();
        for (int i = 0; i < sleepyEntries.size(); i += 2) {
            Entry sleep = sleepyEntries.get(i);
            Entry wake = sleepyEntries.get(i + 1);
            if (!"sleep".equals(sleep.action)) {
                throw new Error("Wrong order");
            }
            if (!"wake".equals(wake.action)) {
                throw new Error("Wrong order");
            }
            int minutes = sleep.time.getMinutes(wake.time);
            for (int j = 0; j < minutes; j++) {
                minuteCounts.compute((sleep.time.minute + j) % 60, (m, c) -> c == null ? 1 : c + 1);
            }
        }
        Integer largestMinute = minuteCounts.entrySet().stream().reduce((a, b) -> a.getValue() > b.getValue() ? a : b).map(Map.Entry::getKey).orElseThrow();
        System.out.println("Part 1 result: " + largestMinute * sleepyGuard);
    }

    private static List<Entry> getEntries() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Paths.get(Day04.class.getResource("/twentyeighteen/Day04-1.txt").toURI()));
        Collections.sort(lines);
        AtomicInteger currentGuard = new AtomicInteger();
        return lines.stream().map(line -> {
            Matcher matcher = LINE.matcher(line);
            if (!matcher.matches()) {
                throw new Error("Could not match line");
            }
            Time time = new Time(
                    Integer.parseInt(matcher.group(1), 10),
                    Integer.parseInt(matcher.group(2), 10),
                    Integer.parseInt(matcher.group(3), 10),
                    Integer.parseInt(matcher.group(4), 10),
                    Integer.parseInt(matcher.group(5), 10)
            );
            String rest = matcher.group(6);
            String action;
            if (rest.startsWith("Guard")) {
                currentGuard.set(Integer.parseInt(rest.substring(7, rest.indexOf(" ", 7))));
                action = "begin";
            } else if (rest.startsWith("wakes")) {
                action = "wake";
            } else if (rest.startsWith("falls")) {
                action = "sleep";
            } else {
                throw new Error("Could not figure out " + rest);
            }
            return new Entry(time, currentGuard.get(), action);
        }).collect(Collectors.toList());
    }

    private static class Entry {
        final Time time;
        final int guard;
        final String action;

        private Entry(Time time, int guard, String action) {
            this.time = time;
            this.guard = guard;
            this.action = action;
        }

        @Override
        public String toString() {
            return "[" + time + "] #" + guard + " " + action;
        }
    }

    private static class Time {
        final int year;
        final int month;
        final int day;
        final int hour;
        final int minute;

        private Time(int year, int month, int day, int hour, int minute) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
        }

        int getMinutes(Time other) {
            if (year != other.year) {
                throw new Error("Different year");
            }
            if (month != other.month) {
                throw new Error("Different month");
            }
            if (day != other.day) {
                throw new Error("Different day");
            }
            return (other.hour - hour) * 60 + other.minute - minute;
        }

        @Override
        public String toString() {
            return String.format("%d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
        }
    }
}
