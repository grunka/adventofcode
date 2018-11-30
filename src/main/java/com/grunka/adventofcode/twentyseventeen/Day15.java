package com.grunka.adventofcode.twentyseventeen;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Day15 {
    private static final BigInteger DIVISOR = new BigInteger("2147483647");
    private static final Function<String, String> PAD = s -> String.format("%32s", s).replace(' ', '0');
    private static final BiFunction<BigInteger, BigInteger, BigInteger> GENERATOR = (in, factor) -> in.multiply(factor).remainder(DIVISOR);
    private static final Function<BigInteger, String> TO_BINARY = in -> PAD.apply(in.toString(2)).substring(16);
    private static final BigInteger A_FACTOR = new BigInteger("16807");
    private static final BigInteger B_FACTOR = new BigInteger("48271");
    private static final BigInteger FOUR = new BigInteger("4");
    private static final BigInteger EIGHT = new BigInteger("8");

    public static void main(String[] args) {
        BigInteger a = new BigInteger("618");
        BigInteger b = new BigInteger("814");

        part1(a, b);
        part2(a, b);
        //part2(new BigInteger("65"), new BigInteger("8921"));
    }

    private static void part2(BigInteger a, BigInteger b) {
        System.out.println("Part 2");
        int count = 0;
        Queue<BigInteger> aQueue = new LinkedList<>();
        Queue<BigInteger> bQueue = new LinkedList<>();
        int pairs = 0;
        while (pairs < 5_000_000) {
            a = GENERATOR.apply(a, A_FACTOR);
            if (a.remainder(FOUR).equals(BigInteger.ZERO)) {
                aQueue.offer(a);
            }
            b = GENERATOR.apply(b, B_FACTOR);
            if (b.remainder(EIGHT).equals(BigInteger.ZERO)) {
                bQueue.offer(b);
            }

            if (!aQueue.isEmpty() && !bQueue.isEmpty()) {
                pairs++;
                if (TO_BINARY.apply(aQueue.remove()).equals(TO_BINARY.apply(bQueue.remove()))) {
                    count++;

                }
            }
        }
        System.out.println("count = " + count);
    }

    private static void part1(BigInteger a, BigInteger b) {
        System.out.println("Part 1");
        int count = 0;
        for (int i = 0; i < 40_000_000; i++) {
            a = GENERATOR.apply(a, A_FACTOR);
            b = GENERATOR.apply(b, B_FACTOR);

            if (TO_BINARY.apply(a).equals(TO_BINARY.apply(b))) {
                count++;
            }
        }
        System.out.println("count = " + count);
    }
}
