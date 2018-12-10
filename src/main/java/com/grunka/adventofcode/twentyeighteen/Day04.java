package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day04 {
    private static final Pattern LINE = Pattern.compile("^\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] (.*)$");

    public static void main(String[] args) throws URISyntaxException, IOException {
        part1();
        part2();
    }

    private static void part2() throws IOException, URISyntaxException {
        // map<minute,map<guard,count>>
        Map<Integer, Map<Integer, Integer>> countsPerMinute = minuteStream().filter(m -> "sleep".equals(m.state)).collect(Collectors.toMap(
                m -> m.minute,
                m -> Map.of(m.guard, 1),
                (a, b) -> Stream.of(a, b)
                        .map(Map::entrySet)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (x, y) -> x + y
                        ))
        ));
        //System.out.println("countsPerMinute = " + countsPerMinute);
        int largestCount = -1;
        int guard = -1;
        int minute = -1;
        for (Map.Entry<Integer, Map<Integer, Integer>> minuteEntry : countsPerMinute.entrySet()) {
            for (Map.Entry<Integer, Integer> guardEntry : minuteEntry.getValue().entrySet()) {
                if (guardEntry.getValue() > largestCount) {
                    largestCount  = guardEntry.getValue();
                    guard = guardEntry.getKey();
                    minute = minuteEntry.getKey();
                }
            }
        }
        //System.out.println("minute = " + minute);
        //System.out.println("guard = " + guard);
        //System.out.println("largestCount = " + largestCount);
        System.out.println("Part 2 result: " + minute * guard);
    }

    private static void part1() throws IOException, URISyntaxException {
        Map<Integer, Integer> counts = minuteStream().filter(s -> "sleep".equals(s.state)).collect(Collectors.toMap(s -> s.guard, s -> 1, (a, b) -> a + b));
        int sleepyGuard = counts.entrySet().stream().reduce((a, b) -> a.getValue() > b.getValue() ? a : b).orElseThrow().getKey();
        Map<Integer, Integer> minuteCounts = minuteStream().filter(s -> s.guard == sleepyGuard).filter(s -> "sleep".equals(s.state)).collect(Collectors.toMap(s -> s.minute, s -> 1, (a, b) -> a + b));
        Integer largestMinute = minuteCounts.entrySet().stream().reduce((a, b) -> a.getValue() > b.getValue() ? a : b).map(Map.Entry::getKey).orElseThrow();
        System.out.println("Part 1 result: " + largestMinute * sleepyGuard);
    }

    private static Stream<MinuteState> minuteStream() throws IOException, URISyntaxException {
        AtomicReference<Entry> previousEntry = new AtomicReference<>();
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
        }).map(current -> {
            Entry previous = previousEntry.get();
            previousEntry.set(current);
            if (previous == null) {
                return Collections.<MinuteState>emptyList();
            } else {
                String state = "begin".equals(previous.action) ? "wake" : previous.action;
                return IntStream.range(previous.time.minute, previous.time.minute + previous.time.getMinutes(current.time))
                        .mapToObj(m -> new MinuteState(previous.guard, m % 60, state))
                        .collect(Collectors.toList());
            }
        }).flatMap(Collection::stream);
    }

    private static class MinuteState {
        final int guard;
        final int minute;
        final String state;

        private MinuteState(int guard, int minute, String state) {
            this.guard = guard;
            this.minute = minute;
            this.state = state;
        }
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
            int dayOffset = 0;
            if (month != other.month) {
                dayOffset = 24 * 60;
            }
            if (day != other.day) {
                dayOffset = (other.day - day) * 24 * 60;
            }
            return (other.hour - hour) * 60 + other.minute - minute + dayOffset;
        }

        @Override
        public String toString() {
            return String.format("%d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
        }
    }
}
