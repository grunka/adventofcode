package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        part1();
        part2();
    }

    private static void part2() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(Day03.class.getResource("/twentyeighteen/Day03-1.txt").toURI()));
        Set<Integer> claimIds = new HashSet<>();
        lines.stream()
                .map(Claim::fromString)
                .peek(c -> claimIds.add(c.id))
                .flatMap(Claim::toPoints)
                .collect(Collectors.toMap(p -> p, p -> Set.of(p.claimId), (a, b) -> Stream.of(a, b).flatMap(Collection::stream).collect(Collectors.toSet())))
                .values().stream().filter(c -> c.size() > 1).forEach(claimIds::removeAll);
        if (claimIds.size() != 1) {
            throw new Error("Found the wrong amount of claims " + claimIds);
        }
        Integer claimId = claimIds.stream().findFirst().get();
        System.out.println("Part 2 result: " + claimId);
    }

    private static void part1() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Paths.get(Day03.class.getResource("/twentyeighteen/Day03-1.txt").toURI()));
        Map<Point, Integer> claimsPerPoint = lines.stream()
                .map(Claim::fromString)
                .flatMap(Claim::toPoints)
                .collect(Collectors.toMap(p -> p, p -> 1, (a, b) -> a + b));
        int multipleClaims = claimsPerPoint.values().stream().filter(i -> i > 1).mapToInt(i -> 1).sum();
        System.out.println("Part 1 result: " + multipleClaims);
    }

    private static class Point {
        final int claimId;
        final int x;
        final int y;

        private Point(int claimId, int x, int y) {
            this.claimId = claimId;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("%d,%d (#%d)", x, y, claimId);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Claim {
        private static final Pattern CLAIM = Pattern.compile("^#([\\d]+) @ ([\\d]+),([\\d]+): ([\\d]+)x([\\d]+)$");

        final int id;
        final int x;
        final int y;
        final int width;
        final int height;

        private Claim(int id, int x, int y, int width, int height) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public static Claim fromString(String input) {
            Matcher matcher = CLAIM.matcher(input);
            if (!matcher.matches()) {
                throw new Error("Input did not match: " + input);
            }
            return new Claim(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5))
            );
        }

        public Stream<Point> toPoints() {
            return Stream.iterate(new Point(id, x, y), p -> p.y != y + height && p.x != x + width, p -> {
                int x2 = ((p.x - x + 1) % width) + x;
                int y2 = x2 == x ? p.y + 1 : p.y;
                return new Point(p.claimId, x2, y2);
            });
        }

        @Override
        public String toString() {
            return String.format("#%d %d,%d: %dx%d", id, x, y, width, height);
        }
    }
}
