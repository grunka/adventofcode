package com.grunka.adventofcode.twentyeighteen;

import java.util.ArrayList;
import java.util.List;

public class Day14 {
    public static void main(String[] args) {
        List<Integer> recipes = new ArrayList<>(List.of(3, 7));
        int elf1 = 0;
        int elf2 = 1;
        int newRecipe = recipes.get(elf1) + recipes.get(elf2);
        if (newRecipe >= 10) {
            recipes.add(newRecipe / 10);
            recipes.add(newRecipe % 10);
        } else {
            recipes.add(newRecipe);
        }
        elf1 = (elf1 + recipes.get(elf1) + 1) % recipes.size();
        elf2 = (elf2 + recipes.get(elf2) + 1) % recipes.size();
    }

    static void print(List<Integer> recipes, int elf1, int elf2) {
        for (int i = 0; i < recipes.size(); i++) {
            int value = recipes.get(i);
            if (i == elf1) {

            } else if (i == elf2) {

            } else {

            }
        }
    }
}
