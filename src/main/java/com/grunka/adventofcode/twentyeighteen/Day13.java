package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day13 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(MethodHandles.lookup().lookupClass().getResource("/twentyeighteen/" + MethodHandles.lookup().lookupClass().getSimpleName() + "-1.txt").toURI()));
        int height = lines.size();
        int width = lines.stream().mapToInt(String::length).max().orElseThrow();

        char[][] track = new char[width][height];
    }
}
