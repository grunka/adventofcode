package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(MethodHandles.lookup().lookupClass().getResource("/twentyeighteen/" + MethodHandles.lookup().lookupClass().getSimpleName() + "-1.txt").toURI()));
        String initialState = lines.get(0).substring("initial state: ".length());
        List<FlowerTransform> transforms = lines.subList(2, lines.size()).stream()
                .map(line -> line.replace(" => ", ""))
                .map(line -> toBooleans(line))
                .map(b -> ())
    }

    private static List<Boolean> toBooleans(String input) {
        return Arrays.stream(input.split("")).map("#"::equals).collect(Collectors.toList());
    }

    interface FlowerTransform {
        boolean apply(boolean l1, boolean l0, boolean c, boolean r0, boolean r1);
    }
}
