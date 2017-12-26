package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Day23 {
    public static void main(String[] args) {
        int[] registers = new int['h' - 'a' + 1];
        registers[0] = 1;
        AtomicInteger programPointer = new AtomicInteger();
        AtomicInteger mulCounter = new AtomicInteger();
        BiConsumer<String, Integer> info = (operation, result) -> {
            if ("jnz".equals(operation)) {
                programPointer.addAndGet(result - 1);
            } else if ("mul".equals(operation)) {
                mulCounter.incrementAndGet();
            }
        };
        List<Runnable> instructions = Arrays.stream(INPUT.split("\n")).map(instruction -> {
            String[] parts = instruction.split(" ");
            int registerX = parts[1].charAt(0) - 'a';
            Supplier<Integer> getY = () -> parts[2].length() == 1 && Character.isLetter(parts[2].charAt(0))
                    ? registers[parts[2].charAt(0) - 'a']
                    : Integer.parseInt(parts[2]);
            switch (parts[0]) {
                case "set":
                    return (Runnable) () -> {
                        int value = getY.get();
                        registers[registerX] = value;
                        info.accept("set", value);
                    };
                case "sub":
                    return (Runnable) () -> {
                        int value = registers[registerX] - getY.get();
                        registers[registerX] = value;
                        info.accept("sub", value);
                    };
                case "mul":
                    return (Runnable) () -> {
                        int value = registers[registerX] * getY.get();
                        registers[registerX] = value;
                        info.accept("mul", value);
                    };
                case "jnz":
                    return (Runnable) () -> {
                        int value = getY.get();
                        int check;
                        if (parts[1].length() == 1 && Character.isLetter(parts[1].charAt(0))) {
                            check = registers[parts[1].charAt(0) - 'a'];
                        } else {
                            check = Integer.parseInt(parts[1]);
                        }
                        if (check != 0) {
                            info.accept("jnz", value);
                        }
                    };
                default:
                    return (Runnable) () -> {
                        throw new UnsupportedOperationException();
                    };
            }
        }).collect(Collectors.toList());
        while (programPointer.get() < instructions.size()) {
            instructions.get(programPointer.getAndIncrement()).run();
        }
        System.out.println("mulCounter = " + mulCounter);
        System.out.println("registers = " + Arrays.toString(registers));
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
