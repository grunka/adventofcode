package com.grunka.adventofcode;

import static com.grunka.adventofcode.Day25.States.*;

public class Day25 {
    public static void main(String[] args) {
        Tape tape = new Tape();
        States state = A;
        for (int step = 0; step < 12368930; step++) {
            switch (state) {
                case A:
                    if (tape.isZero()) {
                        tape.setOne();
                        tape = tape.right();
                        state = B;
                    } else {
                        tape.setZero();
                        tape = tape.right();
                        state = C;
                    }
                    break;
                default:
                    throw new IllegalStateException("Unrecognized state " + state);
            }
        }
        System.out.println("tape.countOnes() = " + tape.countOnes());
    }

    enum States {
        A, B, C, D, E, F;
    }

    private static class Tape {
        private Tape left = null;
        private Tape right = null;
        private boolean value = false;

        boolean isZero() {
            return !value;
        }

        void setOne() {
            value = true;
        }

        void setZero() {
            value = false;
        }

        Tape left() {
            if (left == null) {
                left = new Tape();
            }
            return left;
        }

        Tape right() {
            if (right == null) {
                right = new Tape();
            }
            return right;
        }

        int countOnes() {
            int sum = value ? 1 : 0;
            if (left != null) {
                sum += left.countOnes();
            }
            if (right != null) {
                sum += right.countOnes();
            }
            return sum;
        }
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
