package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Day18 {
    public static void main(String[] args) {
        AtomicLong playingSound = new AtomicLong();
        Program.create(INPUT, 0, playingSound::set, () -> {
            System.out.println(playingSound.get());
            System.exit(0);
            return 0L;
        }).run();
    }

    private static class Program implements Runnable {
        private final List<Runnable> instructions;
        private final AtomicLong programPointer;
        private final HashMap<String, Long> registers;

        private Program(List<Runnable> instructions, AtomicLong programPointer, HashMap<String, Long> registers) {
            this.instructions = instructions;
            this.programPointer = programPointer;
            this.registers = registers;
        }

        public static Program create(String input, long id, Consumer<Long> send, Supplier<Long> receive) {
            AtomicLong programPointer = new AtomicLong();
            HashMap<String, Long> registers = new HashMap<>();
            registers.put("p", id);
            List<Runnable> instructions = Arrays.stream(input.split("\n")).map(row -> {
                String[] arguments = row.substring(4).split(" ");
                Supplier<Long> getX;
                Consumer<Long> setX;
                if (isRegister(arguments[0])) {
                    getX = () -> registers.computeIfAbsent(arguments[0], k -> 0L);
                    setX = i -> registers.put(arguments[0], i);
                } else {
                    long n = Long.parseLong(arguments[0]);
                    getX = () -> n;
                    setX = i -> {
                        throw new UnsupportedOperationException("Tried to set X value to constant on row " + row);
                    };
                }
                Supplier<Long> getY;
                if (arguments.length != 2) {
                    getY = () -> {
                        throw new UnsupportedOperationException("Tried to get Y value on row without Y value " + row);
                    };
                } else {
                    if (isRegister(arguments[1])) {
                        getY = () -> registers.computeIfAbsent(arguments[1], k -> 0L);
                    } else {
                        long n = Long.parseLong(arguments[1]);
                        getY = () -> n;
                    }
                }

                switch (row.substring(0, 3)) {
                    case "snd":
                        return (Runnable) () -> send.accept(getX.get());
                    case "set":
                        return (Runnable) () -> setX.accept(getY.get());
                    case "add":
                        return (Runnable) () -> setX.accept(getX.get() + getY.get());
                    case "mul":
                        return (Runnable) () -> setX.accept(getX.get() * getY.get());
                    case "mod":
                        return (Runnable) () -> setX.accept(getX.get() % getY.get());
                    case "rcv":
                        return (Runnable) () -> setX.accept(receive.get());
                    case "jgz":
                        return (Runnable) () -> {
                            if (getX.get() != 0) {
                                programPointer.addAndGet(getY.get() - 1);
                            }
                        };
                    default:
                        throw new UnsupportedOperationException("Did not recognize operator on row " + row);
                }
            }).collect(Collectors.toList());
            return new Program(instructions, programPointer, registers);
        }

        @Override
        public void run() {
            while (programPointer.get() < instructions.size()) {
                instructions.get((int) programPointer.getAndIncrement()).run();
                //System.out.println("registers = " + registers);
            }
        }
    }

    private static boolean isRegister(String s) {
        return s.length() == 1 && s.charAt(0) >= 'a' && s.charAt(0) <= 'z';
    }

    private static final String TEST_INPUT = "set a 1\n" +
            "add a 2\n" +
            "mul a a\n" +
            "mod a 5\n" +
            "snd a\n" +
            "set a 0\n" +
            "rcv a\n" +
            "jgz a -1\n" +
            "set a 1\n" +
            "jgz a -2";

    private static final String INPUT = "set i 31\n" +
            "set a 1\n" +
            "mul p 17\n" +
            "jgz p p\n" +
            "mul a 2\n" +
            "add i -1\n" +
            "jgz i -2\n" +
            "add a -1\n" +
            "set i 127\n" +
            "set p 680\n" +
            "mul p 8505\n" +
            "mod p a\n" +
            "mul p 129749\n" +
            "add p 12345\n" +
            "mod p a\n" +
            "set b p\n" +
            "mod b 10000\n" +
            "snd b\n" +
            "add i -1\n" +
            "jgz i -9\n" +
            "jgz a 3\n" +
            "rcv b\n" +
            "jgz b -1\n" +
            "set f 0\n" +
            "set i 126\n" +
            "rcv a\n" +
            "rcv b\n" +
            "set p a\n" +
            "mul p -1\n" +
            "add p b\n" +
            "jgz p 4\n" +
            "snd a\n" +
            "set a b\n" +
            "jgz 1 3\n" +
            "snd b\n" +
            "set f 1\n" +
            "add i -1\n" +
            "jgz i -11\n" +
            "snd a\n" +
            "jgz f -16\n" +
            "jgz a -19";
}
