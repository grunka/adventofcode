package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Stack;

public class Day08 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        int[] input = Arrays.stream(Files.readString(Paths.get(Day08.class.getResource("/twentyeighteen/Day08-1.txt").toURI())).trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int position = 0;
        Stack<Tree> treeStack = new Stack<>();
        treeStack.push(new Tree(new Tree[input[position]], new int[input[position + 1]]));
    }

    private static class Tree {
        final Tree[] children;
        final int[] metadata;

        private Tree(Tree[] children, int[] metadata) {
            this.children = children;
            this.metadata = metadata;
        }
    }
}
