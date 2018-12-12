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
        System.out.println("transforms = " + transforms);
        int zeroPosition = 0;
        for (int generation = 0; generation < 20; generation++) {
            if (!state.startsWith(".....")) {
                state = "....." + state;
                zeroPosition += 5;
            }
            if (!state.endsWith(".....")) {
                state = state + ".....";
            }
            System.out.println("state = " + state);
            String newState = "";
            for (int i = 2; i < state.length() - 2; i++) {
                String slice = state.substring(i - 2, i + 3);
                //System.out.println("slice = " + slice);
                newState += transforms.get(slice);
            }
            state = newState;
            System.out.println("state = " + state);
            zeroPosition -= 2;
        }
        System.out.println("state = " + state);
        System.out.println("zeroPosition = " + zeroPosition);
        int sum = 0;
        for (int i = 0; i < state.length(); i++) {
            int pot = i - zeroPosition;
            if (state.charAt(i) == '#') {
                sum += pot;
            }
        }
        System.out.println("Part 1 result: " + sum);
    }
}
