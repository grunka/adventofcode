package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Day08 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        int[] input = Arrays.stream(Files.readString(Paths.get(Day08.class.getResource("/twentyeighteen/Day08-1.txt").toURI())).trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        AtomicInteger sum = new AtomicInteger();
        consume(input, metadata -> Arrays.stream(metadata).forEach(sum::addAndGet));
        System.out.println("Part 1 result: " + sum.get());
    }

    private static int[] consume(int[] input, Consumer<int[]> metadata) {
        int children = input[0];
        int metadataLength = input[1];
        input = Arrays.copyOfRange(input, 2, input.length);
        for (int child = 0; child < children; child++) {
            input = consume(input, metadata);
        }
        metadata.accept(Arrays.copyOfRange(input, 0, metadataLength));
        return Arrays.copyOfRange(input, metadataLength, input.length);
    }
}
