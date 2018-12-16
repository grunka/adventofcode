package com.grunka.adventofcode.twentyeighteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {
    public static void main(String[] args) {
        List<Integer> recipes = new ArrayList<>(List.of(3, 7));
        int elf1 = 0;
        int elf2 = 1;
        //print(recipes, elf1, elf2);
        boolean targetFound = false;
        boolean otherTargetFound = false;
        int target = 846601;
        List<Integer> otherTarget = Arrays.stream(String.valueOf(target).split("")).map(Integer::valueOf).collect(Collectors.toList());
        while (!targetFound || !otherTargetFound) {
            int newRecipe = recipes.get(elf1) + recipes.get(elf2);
            if (newRecipe >= 10) {
                recipes.add(newRecipe / 10);
                recipes.add(newRecipe % 10);
            } else {
                recipes.add(newRecipe);
            }
            elf1 = (elf1 + recipes.get(elf1) + 1) % recipes.size();
            elf2 = (elf2 + recipes.get(elf2) + 1) % recipes.size();
            //print(recipes, elf1, elf2);
            boolean targetIsFound = targetFound || recipes.size() >= target + 10;
            if (!targetFound && targetIsFound) {
                String result = recipes.subList(target, target + 10).stream().map(String::valueOf).collect(Collectors.joining());
                System.out.println("Part 1 result: " + result);
            }
            targetFound = targetFound || targetIsFound;
            boolean otherTargetIsFound = otherTargetFound || recipes.size() > otherTarget.size() && recipes.subList(recipes.size() - otherTarget.size(), recipes.size()).equals(otherTarget);
            if (!otherTargetFound && otherTargetIsFound) {
                System.out.println("Part 2 result: " + (recipes.size() - otherTarget.size()));
            }
            otherTargetFound = otherTargetFound || otherTargetIsFound;
        }
        //print(recipes, elf1, elf2);
        //System.out.println("recipes.size() = " + recipes.size());
    }

    static void print(List<Integer> recipes, int elf1, int elf2) {
        for (int i = 0; i < recipes.size(); i++) {
            int value = recipes.get(i);
            if (i == elf1) {
                System.out.print("(" + value + ")");
            } else if (i == elf2) {
                System.out.print("[" + value + "]");
            } else {
                System.out.print(" " + value + " ");
            }
        }
        System.out.println();
    }
}