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
        Result result = consume(input, metadata -> Arrays.stream(metadata).forEach(sum::addAndGet));
        System.out.println("Part 1 result: " + sum.get());
        System.out.println("Part 2 result: " + result.weight);
    }

    private static Result consume(int[] input, Consumer<int[]> metadataConsumer) {
        int children = input[0];
        int metadataLength = input[1];
        input = Arrays.copyOfRange(input, 2, input.length);
        int[] weights = new int[children];
        for (int child = 0; child < children; child++) {
            Result result = consume(input, metadataConsumer);
            input = result.rest;
            weights[child] = result.weight;
        }
        int[] metadata = Arrays.copyOfRange(input, 0, metadataLength);
        int weight = 0;
        if (children == 0) {
            for (int index : metadata) {
                weight += index;
            }
        } else {
            for (int index : metadata) {
                if (index > 0 && index <= weights.length) {
                    weight += weights[index - 1];
                }
            }
        }
        metadataConsumer.accept(metadata);
        return new Result(weight, Arrays.copyOfRange(input, metadataLength, input.length));
    }

    private static class Result {
        final int weight;
        final int[] rest;

        private Result(int weight, int[] rest) {
            this.weight = weight;
            this.rest = rest;
        }
    }
}
