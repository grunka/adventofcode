package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(MethodHandles.lookup().lookupClass().getResource("/twentyeighteen/" + MethodHandles.lookup().lookupClass().getSimpleName() + "-1.txt").toURI()));
        String state = lines.get(0).substring("initial state: ".length());
        Map<String, String> transforms = lines.subList(2, lines.size()).stream()
                .map(line -> line.split(" => "))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        //System.out.println("transforms = " + transforms);
        System.out.println("Part 1 result: " + doGenerations(state, transforms, 20));
        System.out.println("Part 2 result: " + doGenerations(state, transforms, 50_000_000_000L));
    }

    private static long doGenerations(String state, Map<String, String> transforms, long generations) {
        long zeroPosition = 0;
        for (long generation = 0; generation < generations; generation++) {
            if (!state.startsWith(".....")) {
                state = "....." + state;
                zeroPosition += 5;
            }
            if (!state.endsWith(".....")) {
                state = state + ".....";
            }
            //System.out.println("state = " + state);
            StringBuilder newState = new StringBuilder();
            for (int i = 2; i < state.length() - 2; i++) {
                String slice = state.substring(i - 2, i + 3);
                //System.out.println("slice = " + slice);
                newState.append(transforms.get(slice));
            }
            state = newState.toString();
            //System.out.println("state = " + state);
            zeroPosition -= 2;
        }
        //System.out.println("state = " + state);
        //System.out.println("zeroPosition = " + zeroPosition);
        long sum = 0;
        for (int i = 0; i < state.length(); i++) {
            long pot = i - zeroPosition;
            if (state.charAt(i) == '#') {
                sum += pot;
            }
        }
        return sum;
    }
}
