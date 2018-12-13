package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(MethodHandles.lookup().lookupClass().getResource("/twentyeighteen/" + MethodHandles.lookup().lookupClass().getSimpleName() + "-1.txt").toURI()));
        boolean[] state = getInitialState(lines);
        Tree transforms = new Tree();
        lines.subList(2, lines.size()).stream()
                .map(line -> line.split(" => "))
                .forEach(s -> transforms.add(s[0], s[1]));
        //System.out.println("transforms = " + transforms);
        System.out.println("Part 1 result: " + doGenerations(state, transforms, 20));
        System.out.println("Part 2 result: " + doGenerations(state, transforms, 50_000_000_000L));
    }

    private static class Tree {
        boolean value;
        Tree trueTree;
        Tree falseTree;

        void add(String branch, String value) {
            Tree here = this;
            for (String c : branch.split("")) {
                here = here.get("#".equals(c));
            }
            here.value = "#".equals(value);
        }

        Tree get(boolean tree) {
            if (tree) {
                if (trueTree == null) {
                    trueTree = new Tree();
                }
                return trueTree;
            } else {
                if (falseTree == null) {
                    falseTree = new Tree();
                }
                return falseTree;
            }
        }

        Tree get(boolean[] branch, int start, int length) {
            if (length == 0) {
                return this;
            }
            return get(branch[start]).get(branch, start + 1, length - 1);
        }
    }

    private static boolean[] getInitialState(List<String> lines) {
        String inputState = lines.get(0).substring("initial state: ".length());
        boolean[] state = new boolean[inputState.length()];
        for (int i = 0; i < inputState.length(); i++) {
            state[i] = inputState.charAt(i) == '#';
        }
        return state;
    }

    private static long doGenerations(boolean[] state, Tree transforms, long generations) {
        long zeroPosition = 0;
        String previousState = null;
        for (long generation = 0; generation < generations; generation++) {
            int leadingChange = 5 - countLeadingFalse(state);
            int trailingChange = 5 - countTrailingFalse(state);
            zeroPosition += leadingChange;
            if (leadingChange != 0 || trailingChange != 0) {
                boolean[] paddedState = new boolean[state.length + leadingChange + trailingChange];
                System.arraycopy(state, leadingChange > 0 ? 0 : -leadingChange, paddedState, leadingChange > 0 ? leadingChange : 0, state.length + (leadingChange > 0 ? 0 : leadingChange) + (trailingChange > 0 ? 0 : trailingChange));
                state = paddedState;
            }
            String currentState = toString(state);
            if (currentState.equals(previousState)) {
                zeroPosition += leadingChange * (generations - generation);
                break;
            }
            previousState = currentState;
            print(state, zeroPosition);
            boolean[] nextState = new boolean[state.length];
            for (int i = 2; i < state.length - 2; i++) {
                nextState[i] = transforms.get(state, i - 2, 5).value;
            }
            state = nextState;
        }
        print(state, zeroPosition);
        long sum = 0;
        for (int i = 0; i < state.length; i++) {
            long pot = i - zeroPosition;
            if (state[i]) {
                sum += pot;
            }
        }
        return sum;
    }

    private static int countTrailingFalse(boolean[] state) {
        int trailing = 0;
        while (!state[state.length - trailing - 1]) {
            trailing++;
        }
        return trailing;
    }

    private static int countLeadingFalse(boolean[] state) {
        int leading = 0;
        while (!state[leading]) {
            leading++;
        }
        return leading;
    }

    private static String toString(boolean[] state) {
        char[] text = new char[state.length];
        for (int i = 0; i < state.length; i++) {
            text[i] = state[i] ? '#' : '.';
        }
        return new String(text);
    }

    private static void print(boolean[] state, Object... extra) {
        System.out.println(Stream.concat(Stream.of(toString(state)), Arrays.stream(extra).map(String::valueOf)).collect(Collectors.joining(" ")));
    }
}
