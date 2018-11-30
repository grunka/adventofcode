package com.grunka.adventofcode.twentyseventeen;

import static com.grunka.adventofcode.twentyseventeen.Day25.States.*;

public class Day25 {
    //TODO could write a parser for the input
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
                case B:
                    if (tape.isZero()) {
                        tape.setZero();
                        tape = tape.left();
                        state = A;
                    } else {
                        tape.setZero();
                        tape = tape.right();
                        state = D;
                    }
                    break;
                case C:
                    if (tape.isZero()) {
                        tape.setOne();
                        tape = tape.right();
                        state = D;
                    } else {
                        tape.setOne();
                        tape = tape.right();
                        state = A;
                    }
                    break;
                case D:
                    if (tape.isZero()) {
                        tape.setOne();
                        tape = tape.left();
                        state = E;
                    } else {
                        tape.setZero();
                        tape = tape.left();
                        state = D;
                    }
                    break;
                case E:
                    if (tape.isZero()) {
                        tape.setOne();
                        tape = tape.right();
                        state = F;
                    } else {
                        tape.setOne();
                        tape = tape.left();
                        state = B;
                    }
                    break;
                case F:
                    if (tape.isZero()) {
                        tape.setOne();
                        tape = tape.right();
                        state = A;
                    } else {
                        tape.setOne();
                        tape = tape.right();
                        state = E;
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
                left.right = this;
            }
            return left;
        }

        Tape right() {
            if (right == null) {
                right = new Tape();
                right.left = this;
            }
            return right;
        }

        int countOnes() {
            Tape tape = this;
            while (tape.left != null) {
                tape = tape.left;
            }
            int count = 0;
            while (tape != null) {
                if (tape.value) {
                    count++;
                }
                tape = tape.right;
            }
            return count;
        }

        @Override
        public String toString() {
            return (left != null ? left.leftToString() : "") + (value ? "[1]" : "[0]") + (right != null ? right.rightToString() : "");
        }

        private String leftToString() {
            return (left != null ? left.leftToString() + " " : "") + (value ? "1" : "0");
        }

        private String rightToString() {
            return (value ? "1" : "0") + (right != null ? " " + right.rightToString() : "");
        }
    }

    private static final String TEST_INPUT =
            "Begin in state A.\n" +
                    "Perform a diagnostic checksum after 6 steps.\n" +
                    "\n" +
                    "In state A:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state B.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 0.\n" +
                    "    - Move one slot to the left.\n" +
                    "    - Continue with state B.\n" +
                    "\n" +
                    "In state B:\n" +
                    "  If the current value is 0:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the left.\n" +
                    "    - Continue with state A.\n" +
                    "  If the current value is 1:\n" +
                    "    - Write the value 1.\n" +
                    "    - Move one slot to the right.\n" +
                    "    - Continue with state A.";

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
