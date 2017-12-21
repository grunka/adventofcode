package com.grunka.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 {
    public static void main(String[] args) {
        Map<String, String> rules = new HashMap<>();
        Arrays.stream(INPUT.split("\n")).forEach(rule -> {
            String[] inOut = rule.split(" => ");
            for (String block : permutations(inOut[0])) {
                rules.put(block, inOut[1]);
            }
        });
        String[] blocks = imageToBlocks(IMAGE);
        System.out.println("blocks = " + Arrays.toString(blocks));
        System.out.println("permutations(blocks[0]) = " + permutations(blocks[0]));
        String image = blocksToImage(blocks);
        System.out.println("image = " + image);
        System.out.println("rules = " + rules);
        String[] newBlocks = process(blocks, rules);
        String newImage = blocksToImage(newBlocks);
        System.out.println("newImage = " + newImage);
    }

    private static Collection<String> permutations(String block) {
        Set<String> result = new HashSet<>();
        result.add(block);
        for (int i = 0; i < 100; i++) {
            Set<String> additions = new HashSet<>();
            for (String s : result) {
                additions.add(flipHorizontal(s));
                additions.add(flipVertical(s));
                additions.add(flipHorizontal(flipVertical(s)));
                additions.add(rotateLeft(s));
                additions.add(rotateLeft(rotateLeft(s)));
                additions.add(rotateLeft(rotateLeft(rotateLeft(s))));
            }
            result.addAll(additions);
        }
        System.out.println("result.size() = " + result.size());
        return result;
    }

    private static String[] process(String[] input, Map<String, String> rules) {
        return Arrays.stream(input).map(rules::get).toArray(String[]::new);
    }

    private static String blocksToImage(String[] blocks) {
        int blockSize = blocks[0].indexOf("/");
        String[] lines = new String[blocks.length * blockSize];
        int size = (int) Math.sqrt(blocks.length);
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                for (int line = 0; line < blockSize; line++) {
                    lines[(y * blockSize) + line] = "";
                }
                for (int line = 0; line < blockSize; line++) {
                    lines[(y * blockSize) + line] += blocks[x + y * size].split("/")[line];
                }
            }
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
        if (input.length() == 5) {
            return "" + input.charAt(1) + input.charAt(4) + "/" + input.charAt(0) + input.charAt(3);
        } else if (input.length() == 11) {
            return "" + input.charAt(2) + input.charAt(6) + input.charAt(0) + "/" +
                    input.charAt(1) + input.charAt(5) + input.charAt(9) + "/" +
                    input.charAt(0) + input.charAt(4) + input.charAt(10);

        } else {
            throw new IllegalArgumentException("Unflippable " + input);
        }
    }

    private static String flipHorizontal(String input) {
        if (input.length() == 5) {
            return "" + input.charAt(1) + input.charAt(0) + "/" + input.charAt(4) + input.charAt(3);
        } else if (input.length() == 11) {
            return "" + input.charAt(2) + input.charAt(1) + input.charAt(0) + "/" +
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
