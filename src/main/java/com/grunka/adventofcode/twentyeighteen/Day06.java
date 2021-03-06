package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day06 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<Point> points = Files.readAllLines(Paths.get(Day06.class.getResource("/twentyeighteen/Day06-1.txt").toURI())).stream()
                .map(line -> line.split(", "))
                .map(s -> new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
                .collect(Collectors.toList());
        //System.out.println("points = " + points);

        Box boundingBox = getBoundingBox(points);
        part1(points, boundingBox);
        part2(points, boundingBox);
    }

    private static void part2(List<Point> points, Box boundingBox) {
        Map<Point, Integer> distanceSums = new HashMap<>();
        for (int x = boundingBox.topLeft.x; x <= boundingBox.bottomRight.x; x++) {
            for (int y = boundingBox.topLeft.y; y <= boundingBox.bottomRight.y; y++) {
                Point here = new Point(x, y);
                distanceSums.put(here, points.stream().mapToInt(p -> distance(p, here)).sum());
                //printBox(boundingBox, owners);
            }
        }
        long size = distanceSums.entrySet().stream().filter(e -> e.getValue() < 10_000).count();
        System.out.println("Part 2 result: " + size);
    }

    private static void part1(List<Point> points, Box boundingBox) {
        Map<Point, Integer> owners = new HashMap<>();
        for (int x = boundingBox.topLeft.x; x <= boundingBox.bottomRight.x; x++) {
            for (int y = boundingBox.topLeft.y; y <= boundingBox.bottomRight.y; y++) {
                Point here = new Point(x, y);
                List<Integer> distances = points.stream().map(p -> distance(p, here)).collect(Collectors.toList());
                //System.out.println("distances = " + distances);
                int shortestDistance = distances.stream().reduce((a, b) -> a < b ? a : b).orElseThrow();
                boolean moreThanOne = distances.stream().filter(d -> d == shortestDistance).count() != 1;
                if (!moreThanOne) {
                    owners.put(here, distances.indexOf(shortestDistance));
                }
                //printBox(boundingBox, owners);
            }
        }
        //System.out.println("owners = " + owners);
        //printBox(boundingBox, owners);

        Set<Integer> edgePoints = new HashSet<>();
        for (int x = boundingBox.topLeft.x; x <= boundingBox.bottomRight.x; x++) {
            edgePoints.add(owners.get(new Point(x, boundingBox.topLeft.y)));
            edgePoints.add(owners.get(new Point(x, boundingBox.bottomRight.y)));
        }
        for (int y = boundingBox.topLeft.y; y <= boundingBox.bottomRight.y; y++) {
            edgePoints.add(owners.get(new Point(boundingBox.topLeft.x, y)));
            edgePoints.add(owners.get(new Point(boundingBox.bottomRight.x, y)));
        }
        //System.out.println("edgePoints = " + edgePoints);
        owners.entrySet().removeIf(e -> edgePoints.contains(e.getValue()));
        //printBox(boundingBox, owners);
        //System.out.println("owners = " + owners);
        Map<Integer, Integer> sizes = owners.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, e -> 1, (a, b) -> a + b));
        //System.out.println("sizes = " + sizes);
        Integer largest = sizes.values().stream().reduce((a, b) -> a > b ? a : b).orElseThrow();
        System.out.println("Part 1 result: " + largest);
    }

    private static void printBox(Box boundingBox, Map<Point, Integer> owners) {
        String names = "ABCDEFGHIJKLMNOPQRSTUVXYZ0123456890abcdefghijklmnopqrstuvxyz";
        for (int y = boundingBox.topLeft.y; y <= boundingBox.bottomRight.y; y++) {
            for (int x = boundingBox.topLeft.x; x <= boundingBox.bottomRight.x; x++) {
                Point here = new Point(x, y);
                Integer owner = owners.get(here);
                if (owner == null) {
                    System.out.print(".");
                } else {
                    System.out.print(names.charAt(owner));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Box {
        final Point topLeft;
        final Point bottomRight;

        private Box(Point topLeft, Point bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }
    }

    private static Box getBoundingBox(List<Point> points) {
        Point topLeft = points.get(0);
        Point bottomRight = points.get(0);
        for (Point point : points) {
            if (point.x < topLeft.x) {
                topLeft = new Point(point.x, topLeft.y);
            }
            if (point.y < topLeft.y) {
                topLeft = new Point(topLeft.x, point.y);
            }
            if (point.x > bottomRight.x) {
                bottomRight = new Point(point.x, bottomRight.y);
            }
            if (point.y > bottomRight.y) {
                bottomRight = new Point(bottomRight.x, point.y);
            }
        }
        return new Box(topLeft, bottomRight);
    }

    private static int distance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private static class Point {
        final int x;
        final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
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
}
