package com.grunka.adventofcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day21 {
    public static void main(String[] args) {
        Map<String, String> rules = new HashMap<>();
        Arrays.stream(INPUT.split("\n")).forEach(rule -> {
            String[] inOut = rule.split(" => ");
            rules.put(inOut[0], inOut[1]);
            rules.put(flipVertical(inOut[0]), inOut[1]);
            rules.put(flipHorizontal(inOut[0]), inOut[1]);
            rules.put(rotateRight(inOut[0]), inOut[1]);
            rules.put(rotateLeft(inOut[0]), inOut[1]);
        });
        String[] blocks = imageToBlocks(IMAGE);
        System.out.println("blocks = " + Arrays.toString(blocks));
        String image = blocksToImage(blocks);
        System.out.println("image = " + image);
    }

    private static String blocksToImage(String[] blocks) {
        int blockSize = blocks[0].indexOf("/");
        System.out.println("blockSize = " + blockSize);
        String[] lines = new String[blocks.length * blockSize];
        for (int i = 0; i < lines.length; i++) {

        }
        return Arrays.stream(lines).collect(Collectors.joining("\n"));
    }

    private static String[] imageToBlocks(String image) {
        String[] lines = image.split("\n");
        int size = lines[0].length();
        if (size % 2 == 0) {
            int blockSize = size / 2;
            String[] blocks = new String[blockSize * blockSize];
            for (int y = 0; y < size; y += 2) {
                for (int x = 0; x < size; x += 2) {
                    blocks[(x / 2) + (y / 2) * blockSize] =
                            lines[y].substring(x, x + 2) + "/" +
                                    lines[y + 1].substring(x, x + 2);
                }
            }
            return blocks;
        } else if (size % 3 == 0) {
            int blockSize = size / 3;
            String[] blocks = new String[blockSize * blockSize];
            for (int y = 0; y < size; y += 3) {
                for (int x = 0; x < size; x += 3) {
                    blocks[(x / 3) + (y / 3) * blockSize] =
                            lines[y].substring(x, x + 3) + "/" +
                                    lines[y + 1].substring(x, x + 3) + "/" +
                                    lines[y + 2].substring(x, x + 3);
                }
            }
            return blocks;
        } else {
            throw new IllegalArgumentException("Unrecognized size");
        }
    }

    private static String rotateLeft(String input) {
        return null;
    }

    private static String rotateRight(String input) {
        return null;
    }

    private static String flipHorizontal(String input) {
        if (input.length() == 5) {
            return input.charAt(1) + input.charAt(0) + "/" + input.charAt(4) + input.charAt(3);
        } else if (input.length() == 11) {
            return input.charAt(2) + input.charAt(1) + input.charAt(0) + "/" +
                    input.charAt(6) + input.charAt(5) + input.charAt(4) + "/" +
                    input.charAt(10) + input.charAt(9) + input.charAt(8);

        } else {
            throw new IllegalArgumentException("Unflippable " + input);
        }
    }

    private static String flipVertical(String input) {
        String[] lines = input.split("/");
        if (lines.length == 2) {
            return lines[1] + "/" + lines[0];
        } else if (lines.length == 3) {
            return lines[2] + "/" + lines[1] + "/" + lines[0];
        } else {
            throw new IllegalArgumentException("Unflippable " + input);
        }
    }

    private static final String IMAGE =
            ".#.\n" +
                    "..#\n" +
                    "###";
    private static final String INPUT =
            "../.. => ..#/.#./...\n" +
                    "#./.. => .../#../.##\n" +
                    "##/.. => .##/###/##.\n" +
                    ".#/#. => #.#/..#/#.#\n" +
                    "##/#. => .../.##/...\n" +
                    "##/## => ##./..#/..#\n" +
                    ".../.../... => ##../..../##../.###\n" +
                    "#../.../... => ...#/.#.#/.#../.#.#\n" +
                    ".#./.../... => #.#./...#/#.#./.##.\n" +
                    "##./.../... => ..#./#.##/#.../.###\n" +
                    "#.#/.../... => ##../##.#/..#./#.##\n" +
                    "###/.../... => ..../.#.#/.###/#..#\n" +
                    ".#./#../... => #..#/#.../.##./....\n" +
                    "##./#../... => #.##/..##/####/.###\n" +
                    "..#/#../... => ..#./#.##/####/####\n" +
                    "#.#/#../... => .##./#.##/#.#./##.#\n" +
                    ".##/#../... => #.##/####/.###/...#\n" +
                    "###/#../... => ..../#.#./##.#/..##\n" +
                    ".../.#./... => .###/.##./##../.##.\n" +
                    "#../.#./... => ..../#.##/...#/#.#.\n" +
                    ".#./.#./... => ...#/####/.##./#...\n" +
                    "##./.#./... => .###/#.##/###./....\n" +
                    "#.#/.#./... => #.##/###./..../..#.\n" +
                    "###/.#./... => .#../#.#./#.##/#.##\n" +
                    ".#./##./... => .###/##../..##/#..#\n" +
                    "##./##./... => ..#./#.#./.#.#/##.#\n" +
                    "..#/##./... => .#../####/...#/..##\n" +
                    "#.#/##./... => ..../##.#/.##./....\n" +
                    ".##/##./... => .#.#/.#.#/.##./####\n" +
                    "###/##./... => ##.#/..../..../....\n" +
                    ".../#.#/... => ..##/##../##.#/###.\n" +
                    "#../#.#/... => ####/#.##/#.../###.\n" +
                    ".#./#.#/... => ..../#..#/..##/.#..\n" +
                    "##./#.#/... => #.../..##/##../..#.\n" +
                    "#.#/#.#/... => ...#/#.#./#.#./#...\n" +
                    "###/#.#/... => ###./###./##.#/###.\n" +
                    ".../###/... => ..#./###./##.#/####\n" +
                    "#../###/... => ##.#/..#./##../..##\n" +
                    ".#./###/... => #.../#.##/##../....\n" +
                    "##./###/... => ..##/.#.#/#..#/#.##\n" +
                    "#.#/###/... => #.##/..#./.#../..##\n" +
                    "###/###/... => ..#./#..#/####/.##.\n" +
                    "..#/.../#.. => ##.#/#.##/...#/###.\n" +
                    "#.#/.../#.. => #..#/..#./##../###.\n" +
                    ".##/.../#.. => ..#./.#../###./#.#.\n" +
                    "###/.../#.. => ...#/...#/.#.#/.##.\n" +
                    ".##/#../#.. => ##../#.#./#..#/##..\n" +
                    "###/#../#.. => ##../.#.#/##../#..#\n" +
                    "..#/.#./#.. => ##.#/##.#/...#/.#..\n" +
                    "#.#/.#./#.. => .###/.#.#/###./....\n" +
                    ".##/.#./#.. => #..#/###./####/..#.\n" +
                    "###/.#./#.. => ..#./.###/.###/...#\n" +
                    ".##/##./#.. => #.##/..##/...#/.###\n" +
                    "###/##./#.. => ####/##.#/#.##/#..#\n" +
                    "#../..#/#.. => ..../.##./#.##/#...\n" +
                    ".#./..#/#.. => #..#/##../...#/#...\n" +
                    "##./..#/#.. => ..#./.###/..##/.#.#\n" +
                    "#.#/..#/#.. => .##./..##/..#./#..#\n" +
                    ".##/..#/#.. => ####/.#.#/#.../.#.#\n" +
                    "###/..#/#.. => ..../..##/#.##/###.\n" +
                    "#../#.#/#.. => #.##/.#.#/.#../.##.\n" +
                    ".#./#.#/#.. => ..##/###./.###/###.\n" +
                    "##./#.#/#.. => ##.#/##.#/#.#./##..\n" +
                    "..#/#.#/#.. => ###./###./.#.#/.#..\n" +
                    "#.#/#.#/#.. => ##../..#./##../....\n" +
                    ".##/#.#/#.. => .###/#.#./##.#/##..\n" +
                    "###/#.#/#.. => ##.#/#.#./.#.#/#...\n" +
                    "#../.##/#.. => .#.#/...#/.#.#/..#.\n" +
                    ".#./.##/#.. => ###./##../##.#/....\n" +
                    "##./.##/#.. => ..##/###./#.#./#.#.\n" +
                    "#.#/.##/#.. => ##.#/..##/#..#/####\n" +
                    ".##/.##/#.. => ..../####/..#./##..\n" +
                    "###/.##/#.. => .###/#..#/..../.#..\n" +
                    "#../###/#.. => #..#/.#../.#.#/#...\n" +
                    ".#./###/#.. => .#../..../.##./.###\n" +
                    "##./###/#.. => ##.#/.#../.#.#/#..#\n" +
                    "..#/###/#.. => #.##/##../..##/#...\n" +
                    "#.#/###/#.. => ####/..##/.#../##.#\n" +
                    ".##/###/#.. => .###/#..#/.###/#.##\n" +
                    "###/###/#.. => ..##/.##./##../#..#\n" +
                    ".#./#.#/.#. => ..##/.##./.##./.###\n" +
                    "##./#.#/.#. => ..##/...#/.##./####\n" +
                    "#.#/#.#/.#. => .###/.###/#.#./.#..\n" +
                    "###/#.#/.#. => ##.#/###./##.#/####\n" +
                    ".#./###/.#. => ...#/..#./.#.#/.#..\n" +
                    "##./###/.#. => ###./##.#/#.../#.#.\n" +
                    "#.#/###/.#. => .##./#.#./...#/..#.\n" +
                    "###/###/.#. => .#.#/.#../..##/####\n" +
                    "#.#/..#/##. => .##./...#/#..#/.###\n" +
                    "###/..#/##. => #.##/.#.#/...#/..##\n" +
                    ".##/#.#/##. => ###./.###/...#/....\n" +
                    "###/#.#/##. => .##./.##./#.#./#...\n" +
                    "#.#/.##/##. => #.#./.##./.#.#/.###\n" +
                    "###/.##/##. => ..../####/.#.#/#.##\n" +
                    ".##/###/##. => .##./.###/###./.#..\n" +
                    "###/###/##. => #.../###./.##./##.#\n" +
                    "#.#/.../#.# => #.#./..../#.##/###.\n" +
                    "###/.../#.# => .#../.#.#/#.../.###\n" +
                    "###/#../#.# => ###./#..#/####/##..\n" +
                    "#.#/.#./#.# => ###./##.#/..../.#..\n" +
                    "###/.#./#.# => ####/.#.#/.#../..##\n" +
                    "###/##./#.# => #.#./####/..##/#...\n" +
                    "#.#/#.#/#.# => #.#./#.#./#.../#.##\n" +
                    "###/#.#/#.# => #.##/.#../..#./.##.\n" +
                    "#.#/###/#.# => .###/..##/####/#..#\n" +
                    "###/###/#.# => #.../..#./..#./#.##\n" +
                    "###/#.#/### => .#.#/.###/#.##/..##\n" +
                    "###/###/### => #.#./...#/.#../.#.#";
}
