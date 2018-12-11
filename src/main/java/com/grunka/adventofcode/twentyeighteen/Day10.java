package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day10 {
    private static final Pattern LINE = Pattern.compile("^position=<[ ]*([-]?[0-9]+),[ ]*([-]?[0-9]+)> velocity=<[ ]*([-]?[0-9]+),[ ]*([-]?[0-9]+)>$");

    public static void main(String[] args) throws URISyntaxException, IOException {
        List<Point> points = Files.readAllLines(Paths.get(Day10.class.getResource("/twentyeighteen/Day10-1.txt").toURI())).stream()
                //.peek(System.out::println)
                .map(LINE::matcher)
                .peek(m -> {
                    if (!m.matches()) {
                        throw new Error("Non matching line " + m.toString());
                    }
                })
                .map(m -> new Point(
                        Long.parseLong(m.group(1)),
                        Long.parseLong(m.group(2)),
                        Long.parseLong(m.group(3)),
                        Long.parseLong(m.group(4))
                ))
                .collect(Collectors.toList());

        boolean smaller = true;
        long seconds = 0;
        while (smaller) {
            long before = size(points);
            points.forEach(Point::next);
            long after = size(points);
            smaller = after < before;
            if (smaller) {
                seconds++;
            }
            //System.out.println("after = " + after);
        }
        points.forEach(Point::previous);
        System.out.println("Part 1 result:");
        print(points);
        System.out.println("Part 2 result: " + seconds);
    }

    interface BoxFunction<T> {
        T apply(long minX, long maxX, long minY, long maxY);
    }

    private static <T> T processBox(List<Point> points, BoxFunction<T> boxFunction) {
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        for (Point point : points) {
            if (point.x < minX) {
                minX = point.x;
            }
            if (point.x > maxX) {
                maxX = point.x;
            }
            if (point.y < minY) {
                minY = point.y;
            }
            if (point.y > maxY) {
                maxY = point.y;
            }
        }
        return boxFunction.apply(minX, maxX, minY, maxY);
    }

    private static long size(List<Point> points) {
        return processBox(points, (minX, maxX, minY, maxY) -> (maxX - minX) * (maxY - minY));
    }

    private static void print(List<Point> points) {
        System.out.println((String) processBox(points, (minX, maxX, minY, maxY) -> {
            StringBuilder result = new StringBuilder();
            for (long y = minY; y <= maxY; y++) {
                for (long x = minX; x <= maxX; x++) {
                    long x0 = x;
                    long y0 = y;
                    boolean point = points.stream().anyMatch(p -> p.x == x0 && p.y == y0);
                    result.append(point ? "#" : ".");
                }
                result.append("\n");
            }
            return result.toString();
        }));
    }

    private static class Point {
        long x;
        long y;

        final long vx;
        final long vy;

        Point(long x, long y, long vx, long vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        void next() {
            x += vx;
            y += vy;
        }

        void previous() {
            x -= vx;
            y -= vy;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", vx=" + vx +
                    ", vy=" + vy +
                    '}';
        }
    }
}
