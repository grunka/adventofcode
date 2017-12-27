package com.grunka.adventofcode;

public class Day25 {
    public static void main(String[] args) {
    }

    private static final String INPUT =
            "Begin in state A.\n" +
                    "Perform a diagnostic checksum after 12368930 steps.\n" +
                    "\n" +
                    "In state A:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state B.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 0.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state C.\n" +
                    "\n" +
                    "In state B:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 0.\n" +
                    "    - Move one slot to the left.\n" +
                    "    - Continue with state A.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 0.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state D.\n" +
                    "\n" +
                    "In state C:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state D.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state A.\n" +
                    "\n" +
                    "In state D:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the left.\n" +
                    "    - Continue with state E.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 0.\n" +
                    "    - Move one slot to the left.\n" +
                    "    - Continue with state D.\n" +
                    "\n" +
                    "In state E:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state F.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the left.\n" +
                    "    - Continue with state B.\n" +
                    "\n" +
                    "In state F:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state A.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state E.";
}
