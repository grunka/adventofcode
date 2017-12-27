package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Day23 {
    private static void part2() {
        int count = 0;
        for (int i = 108400; i <= 125400; i += 17) {
            for (int a = 2; a < Math.sqrt(i); a++) {
                if (i % a == 0) {
                    count++;
                    break;
                }
            }
        }
        System.out.println("count = " + count);
    }

    public static void main(String[] args) {
        part2();
        System.exit(1);
        int[] registers = new int['h' - 'a' + 1];
        registers[0] = 0;
        AtomicInteger programPointer = new AtomicInteger();
        AtomicInteger mulCounter = new AtomicInteger();
        List<Runnable> instructions = parseProgram(registers, programPointer, mulCounter);
        runProgram(programPointer, instructions, registers);
        System.out.println("mulCounter = " + mulCounter);
        System.out.println("registers = " + Arrays.toString(registers));
    }

    private static List<Runnable> parseProgram(int[] registers, AtomicInteger programPointer, AtomicInteger mulCounter) {
        Consumer<Integer> jumpHandler = offset -> programPointer.addAndGet(offset - 1);
        return Arrays.stream(INPUT.split("\n")).map(instruction -> {
            String[] parts = instruction.split(" ");
            int registerX = parts[1].charAt(0) - 'a';
            Supplier<Integer> getY;
            if (isRegister(parts[2])) {
                getY = () -> registers[parts[2].charAt(0) - 'a'];
            } else {
                int constantY = parse(parts[2]);
                getY = () -> constantY;
            }
            switch (parts[0]) {
                case "set":
                    return (Runnable) () -> registers[registerX] = getY.get();
                case "sub":
                    return (Runnable) () -> registers[registerX] = registers[registerX] - getY.get();
                case "mul":
                    return (Runnable) () -> {
                        registers[registerX] = registers[registerX] * getY.get();
                        mulCounter.incrementAndGet();
                    };
                case "jnz":
                    Supplier<Boolean> nonZeroX;
                    if (isRegister(parts[1])) {
                        nonZeroX = () -> registers[parts[1].charAt(0) - 'a'] != 0;
                    } else {
                        int constantX = Integer.parseInt(parts[1]);
                        nonZeroX = () -> constantX != 0;
                    }
                    return (Runnable) () -> {
                        if (nonZeroX.get()) {
                            jumpHandler.accept(getY.get());
                        }
                    };
                default:
                    return (Runnable) () -> {
                        throw new UnsupportedOperationException();
                    };
            }
        }).collect(Collectors.toList());
    }

    private static void runProgram(AtomicInteger programPointer, List<Runnable> instructions, int[] registers) {
        while (programPointer.get() < instructions.size()) {
            instructions.get(programPointer.getAndIncrement()).run();
            System.out.println("registers = " + Arrays.toString(registers));
        }
    }

    private static boolean isRegister(String input) {
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    private static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static final String INPUT =
            "set b 84\n" +
                    "set c b\n" +
                    "jnz a 2\n" +
                    "jnz 1 5\n" +
                    "mul b 100\n" +
                    "sub b -100000\n" +
                    "set c b\n" +
                    "sub c -17000\n" +
                    "set f 1\n" +
                    "set d 2\n" +
                    "set e 2\n" +
                    "set g d\n" +
                    "mul g e\n" +
                    "sub g b\n" +
                    "jnz g 2\n" +
                    "set f 0\n" +
                    "sub e -1\n" +
                    "set g e\n" +
                    "sub g b\n" +
                    "jnz g -8\n" +
                    "sub d -1\n" +
                    "set g d\n" +
                    "sub g b\n" +
                    "jnz g -13\n" +
                    "jnz f 2\n" +
                    "sub h -1\n" +
                    "set g b\n" +
                    "sub g c\n" +
                    "jnz g 2\n" +
                    "jnz 1 3\n" +
                    "sub b -17\n" +
                    "jnz 1 -23";
}
