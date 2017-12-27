package com.grunka.adventofcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day24 {
    public static void main(String[] args) {
        List<Port> ports = Arrays.stream(INPUT.split("\n")).map(row -> {
            String[] port = row.split("/");
            return new Port(Integer.parseInt(port[0]), Integer.parseInt(port[1]));
        }).collect(Collectors.toList());

        List<Port> strongestBridge = findStrongestBridge(0, ports, Collections.emptyList());
        System.out.println("strongestBridge = " + strongestBridge);
        System.out.println("strength(strongestBridge) = " + strength(strongestBridge));

        List<Port> longestBridge = findLongestBridge(0, ports, Collections.emptyList());
        System.out.println("longestBridge = " + longestBridge);
        System.out.println("strength(longestBridge) = " + strength(longestBridge));
    }

    private static List<Port> findStrongestBridge(int next, List<Port> ports, List<Port> bridge) {
        List<Port> possibilities = ports.stream()
                .filter(p -> !bridge.contains(p))
                .filter(p -> p.has(next))
                .collect(Collectors.toList());
        if (possibilities.isEmpty()) {
            return bridge;
        }
        List<Port> strongest = Collections.emptyList();
        for (Port possibility : possibilities) {
            List<Port> attempt = new ArrayList<>(bridge);
            attempt.add(possibility);
            List<Port> possibleStrongest = findStrongestBridge(possibility.other(next), ports, attempt);
            int previous = strength(strongest);
            int current = strength(possibleStrongest);
            if (current > previous) {
                strongest = possibleStrongest;
            }
        }
        return strongest;
    }

    private static List<Port> findLongestBridge(int next, List<Port> ports, List<Port> bridge) {
        List<Port> possibilities = ports.stream()
                .filter(p -> !bridge.contains(p))
                .filter(p -> p.has(next))
                .collect(Collectors.toList());
        if (possibilities.isEmpty()) {
            return bridge;
        }
        List<Port> longest = Collections.emptyList();
        for (Port possibility : possibilities) {
            List<Port> attempt = new ArrayList<>(bridge);
            attempt.add(possibility);
            List<Port> possibleLongest = findLongestBridge(possibility.other(next), ports, attempt);
            if (longest.size() < possibleLongest.size()) {
                longest = possibleLongest;
            }
            if (longest.size() == possibleLongest.size()) {
                if (strength(possibleLongest) > strength(longest)) {
                    longest = possibleLongest;
                }
            }
        }
        return longest;
    }

    private static int strength(List<Port> bridge) {
        return bridge.stream().mapToInt(Port::strength).sum();
    }

    private static class Port {
        final int a;
        final int b;

        private Port(int a, int b) {
            this.a = a;
            this.b = b;
        }

        boolean has(int i) {
            return a == i || b == i;
        }

        public int strength() {
            return a + b;
        }

        public int other(int i) {
            if (i == a) {
                return b;
            }
            if (i == b) {
                return a;
            }
            throw new IllegalArgumentException("Did not match any port");
        }

        @Override
        public String toString() {
            return "Port{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    private static final String INPUT = "42/37\n" +
            "28/28\n" +
            "29/25\n" +
            "45/8\n" +
            "35/23\n" +
            "49/20\n" +
            "44/4\n" +
            "15/33\n" +
            "14/19\n" +
            "31/44\n" +
            "39/14\n" +
            "25/17\n" +
            "34/34\n" +
            "38/42\n" +
            "8/42\n" +
            "15/28\n" +
            "0/7\n" +
            "49/12\n" +
            "18/36\n" +
            "45/45\n" +
            "28/7\n" +
            "30/43\n" +
            "23/41\n" +
            "0/35\n" +
            "18/9\n" +
            "3/31\n" +
            "20/31\n" +
            "10/40\n" +
            "0/22\n" +
            "1/23\n" +
            "20/47\n" +
            "38/36\n" +
            "15/8\n" +
            "34/32\n" +
            "30/30\n" +
            "30/44\n" +
            "19/28\n" +
            "46/15\n" +
            "34/50\n" +
            "40/20\n" +
            "27/39\n" +
            "3/14\n" +
            "43/45\n" +
            "50/42\n" +
            "1/33\n" +
            "6/39\n" +
            "46/44\n" +
            "22/35\n" +
            "15/20\n" +
            "43/31\n" +
            "23/23\n" +
            "19/27\n" +
            "47/15\n" +
            "43/43\n" +
            "25/36\n" +
            "26/38\n" +
            "1/10";
}
