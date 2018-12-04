package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 {
    private static final Pattern LINE = Pattern.compile("^\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] (.*)$");

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<Entry> entries = getEntries();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 1; i < entries.size(); i++) {
            Entry e = entries.get(i);
            if ("wake".equals(e.action)) {
                int minutes = e.time.getMinutes(entries.get(i - 1).time);
                counts.compute(e.guard, (k, count) -> count == null ? minutes : minutes + count);
            }
        }
        Integer sleepyGuard = counts.entrySet().stream().reduce((a, b) -> a.getValue() > b.getValue() ? a : b).orElseThrow().getKey();
        List<Entry> guardEntries = entries.stream().filter(e -> e.guard == sleepyGuard).collect(Collectors.toList());
        long largestCount = -1;
        int largestMinute = -1;
        for (int i = 0; i < 59; i++) {
            int minute = i;
            long minuteCount = guardEntries.stream().filter(e -> e.time.minute == minute).count();
            if (minuteCount > largestCount) {
                largestCount = minuteCount;
                largestMinute = minute;
            }
        }
        System.out.println("largestCount = " + largestCount);
        System.out.println("largestMinute = " + largestMinute);
        System.out.println("sleepyGuard = " + sleepyGuard);
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
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5))
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
            return "Entry{" +
                    "time=" + time +
                    ", guard=" + guard +
                    ", action='" + action + '\'' +
                    '}';
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

        public int getMinutes(Time other) {
            if (year != other.year) {
                throw new Error("Different year");
            }
            if (month != other.month) {
                throw new Error("Different month");
            }
            if (day != other.day) {
                throw new Error("Different day");
            }
            return (other.hour - hour) * 60 + other.minute + minute + 1;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    ", hour=" + hour +
                    ", minute=" + minute +
                    '}';
        }
    }
}
