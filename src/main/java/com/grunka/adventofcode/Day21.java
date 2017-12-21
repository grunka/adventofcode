package com.grunka.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 {
    public static void main(String[] args) {
        assert "24/13".equals(rotateLeft("12/34"));
        assert "369/258/147".equals(rotateLeft("123/456/789"));
        assert "21/43".equals(flipHorizontal("12/34"));
        assert "34/12".equals(flipVertical("12/34"));
        assert "321/654/987".equals(flipHorizontal("123/456/789"));
        assert "789/456/123".equals(flipVertical("123/456/789"));

        Map<String, String> rules = new HashMap<>();
        Arrays.stream(INPUT.split("\n")).forEach(rule -> {
            String[] inOut = rule.split(" => ");
            for (String block : permutations(inOut[0])) {
                rules.put(block, inOut[1]);
            }
        });
        String fiveIterations = iterateImage(rules, IMAGE, 5, true);
        System.out.println("countOn(fiveIterations) = " + countOn(fiveIterations));

        String eighteenIterations = iterateImage(rules, IMAGE, 18, false);
        System.out.println("countOn(eighteenIterations) = " + countOn(eighteenIterations));
    }

    private static int countOn(String image) {
        return image.replaceAll("\\.", "").replaceAll("\n", "").length();
    }

    private static String iterateImage(Map<String, String> rules, String image, int iterations, boolean print) {
        for (int i = 0; i < iterations; i++) {
            image = blocksToImage(process(imageToBlocks(image), rules));
            if (print) {
                System.out.println(image);
                System.out.println();
            }
        }
        return image;
    }

    private static Collection<String> permutations(String block) {
        //TODO this is brute forced, maybe make it not be
        Set<String> result = new HashSet<>();
        result.add(block);
        for (int i = 0; i < 3; i++) {
            Set<String> additions = new HashSet<>();
            for (String s : result) {
                additions.add(flipHorizontal(s));
                additions.add(flipVertical(s));
                additions.add(rotateLeft(s));
                additions.add(rotateLeft(rotateLeft(s)));
                additions.add(rotateLeft(rotateLeft(rotateLeft(s))));
            }
            result.addAll(additions);
        }
        return result;
    }

    private static String[] process(String[] input, Map<String, String> rules) {
        return Arrays.stream(input).map(rules::get).toArray(String[]::new);
    }

    private static String blocksToImage(String[] blocks) {
        int size = (int) Math.sqrt(blocks.length);
        int blockSize = blocks[0].indexOf("/");
        String[] lines = new String[size * blockSize];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                String block = blocks[column + row * size];
                String[] segments = block.split("/");
                for (int segment = 0; segment < segments.length; segment++) {
                    int line = row * blockSize + segment;
                    if (lines[line] == null) {
                        lines[line] = segments[segment];
                    } else {
                        lines[line] += segments[segment];
                    }
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
            return "" + input.charAt(2) + input.charAt(6) + input.charAt(10) + "/" +
                    input.charAt(1) + input.charAt(5) + input.charAt(9) + "/" +
                    input.charAt(0) + input.charAt(4) + input.charAt(8);

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
