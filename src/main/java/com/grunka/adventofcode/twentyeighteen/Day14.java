package com.grunka.adventofcode.twentyeighteen;

import java.util.Arrays;
import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        int[] recipes = new int[]{3, 7};
        int elf1 = 0;
        int elf2 = 1;
        //print(recipes, elf1, elf2);
        boolean targetFound = false;
        boolean otherTargetFound = false;
        int target = 846601;
        int[] otherTarget = Arrays.stream(String.valueOf(target).split("")).mapToInt(Integer::valueOf).toArray();
        while (!targetFound || !otherTargetFound) {
            int newRecipe = recipes[elf1] + recipes[elf2];
            if (newRecipe >= 10) {
                recipes = Arrays.copyOf(recipes, recipes.length + 2);
                recipes[recipes.length - 2] = newRecipe / 10;
                recipes[recipes.length - 1] = newRecipe % 10;
            } else {
                recipes = Arrays.copyOf(recipes, recipes.length + 1);
                recipes[recipes.length - 1] = newRecipe;
            }
            elf1 = (elf1 + recipes[elf1] + 1) % recipes.length;
            elf2 = (elf2 + recipes[elf2] + 1) % recipes.length;
            //print(recipes, elf1, elf2);
            boolean targetIsFound = targetFound || recipes.length >= target + 10;
            if (!targetFound && targetIsFound) {
                System.out.print("Part 1 result: ");
                for (int i = 0; i < 10; i++) {
                    System.out.print(recipes[target + i]);
                }
                System.out.println();
            }
            targetFound = targetFound || targetIsFound;
            boolean otherTargetIsFound = otherTargetFound || recipes.length > otherTarget.length && Arrays.equals(recipes, recipes.length - otherTarget.length, recipes.length, otherTarget, 0, otherTarget.length);
            if (!otherTargetFound && otherTargetIsFound) {
                System.out.println("Part 2 result: " + (recipes.length - otherTarget.length));
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
