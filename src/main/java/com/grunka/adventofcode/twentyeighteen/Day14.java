package com.grunka.adventofcode.twentyeighteen;

import java.util.Arrays;

public class Day14 {
    public static void main(String[] args) {
        int[] recipes = new int[]{3, 7};
        int recipes_length = 2;
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
                recipes_length += 2;
                if (recipes_length > recipes.length) {
                    recipes = Arrays.copyOf(recipes, recipes.length + 100);
                }
                recipes[recipes_length - 2] = newRecipe / 10;
                recipes[recipes_length - 1] = newRecipe % 10;
            } else {
                recipes_length++;
                if (recipes_length > recipes.length) {
                    recipes = Arrays.copyOf(recipes, recipes.length + 100);
                }
                recipes[recipes_length - 1] = newRecipe;
            }
            elf1 = (elf1 + recipes[elf1] + 1) % recipes_length;
            elf2 = (elf2 + recipes[elf2] + 1) % recipes_length;
            //print(recipes, recipes_length, elf1, elf2);
            boolean targetIsFound = targetFound || recipes_length >= target + 10;
            if (!targetFound && targetIsFound) {
                System.out.print("Part 1 result: ");
                for (int i = 0; i < 10; i++) {
                    System.out.print(recipes[target + i]);
                }
                System.out.println();
            }
            targetFound = targetFound || targetIsFound;
            boolean otherTargetIsFound = otherTargetFound || recipes_length > otherTarget.length && Arrays.equals(recipes, recipes_length - otherTarget.length, recipes_length, otherTarget, 0, otherTarget.length);
            if (!otherTargetFound && otherTargetIsFound) {
                System.out.println("Part 2 result: " + (recipes_length - otherTarget.length));
            }
            otherTargetFound = otherTargetFound || otherTargetIsFound;
        }
        //print(recipes, elf1, elf2);
        //System.out.println("recipes.size() = " + recipes.size());
    }

    static void print(int[] recipes, int recipes_length, int elf1, int elf2) {
        for (int i = 0; i < recipes_length; i++) {
            int value = recipes[i];
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
