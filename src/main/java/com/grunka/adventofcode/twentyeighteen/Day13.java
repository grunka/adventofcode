package com.grunka.adventofcode.twentyeighteen;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class Day13 {
    public static void main(String[] args) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(MethodHandles.lookup().lookupClass().getResource("/twentyeighteen/" + MethodHandles.lookup().lookupClass().getSimpleName() + "-1.txt").toURI()));
        int height = lines.size();
        int width = lines.stream().mapToInt(String::length).max().orElseThrow();

        char[][] track = new char[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                String row = lines.get(y);
                if (x < row.length()) {
                    track[x][y] = row.charAt(x);
                } else {
                    track[x][y] = ' ';
                }
            }
        }

        List<Cart> carts = getCarts(track, width, height);
        Collections.sort(carts);
        List<Cart> movedCarts = new ArrayList<>();
        boolean firstCollision = true;
        while (!carts.isEmpty()) {
            while (!carts.isEmpty()) {
                Cart movedCart = carts.remove(0).move(track);
                Optional<Cart> collided = Stream.of(movedCarts, carts).flatMap(Collection::stream).filter(c -> c.x == movedCart.x && c.y == movedCart.y).findFirst();
                if (collided.isPresent()) {
                    if (firstCollision) {
                        System.out.println("Part 1 result: " + movedCart.x + "," + movedCart.y);
                        firstCollision = false;
                    }
                    carts.remove(collided.get());
                    movedCarts.remove(collided.get());

                } else {
                    movedCarts.add(movedCart);
                }
            }
            carts.addAll(movedCarts);
            movedCarts.clear();
            Collections.sort(carts);
            if (carts.size() == 1) {
                Cart lastCart = carts.remove(0);
                System.out.println("Part 2 result: " + lastCart.x + "," + lastCart.y);
            }
        }
    }

    private static List<Cart> getCarts(char[][] track, int width, int height) {
        List<Cart> carts = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (track[x][y]) {
                    case '<':
                        track[x][y] = '-';
                        carts.add(Cart.create(x, y, Direction.LEFT));
                        break;
                    case '>':
                        track[x][y] = '-';
                        carts.add(Cart.create(x, y, Direction.RIGHT));
                        break;
                    case '^':
                        track[x][y] = '|';
                        carts.add(Cart.create(x, y, Direction.UP));
                        break;
                    case 'v':
                        track[x][y] = '|';
                        carts.add(Cart.create(x, y, Direction.DOWN));
                        break;
                    default:
                }
            }
        }
        return carts;
    }

    private static class Cart implements Comparable<Cart> {
        final String uuid;
        final int x;
        final int y;
        final Direction direction;
        final Choice choice;

        static Cart create(int x, int y, Direction direction) {
            return new Cart(UUID.randomUUID().toString(), x, y, direction, Choice.LEFT);
        }

        private Cart(String uuid, int x, int y, Direction direction, Choice choice) {
            this.uuid = uuid;
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.choice = choice;
        }

        Cart move(char[][] track) {
            int nextX = x;
            int nextY = y;
            switch (direction) {
                case UP:
                    nextY--;
                    break;
                case DOWN:
                    nextY++;
                    break;
                case LEFT:
                    nextX--;
                    break;
                case RIGHT:
                    nextX++;
                    break;
            }
            Direction nextDirection = direction;
            Choice nextChoice = choice;
            switch (track[nextX][nextY]) {
                case '/':
                    nextDirection = direction == Direction.LEFT || direction == Direction.RIGHT ? direction.left() : direction.right();
                    break;
                case '\\':
                    nextDirection = direction == Direction.LEFT || direction == Direction.RIGHT ? direction.right() : direction.left();
                    break;
                case '+':
                    nextDirection = choice.choose(direction);
                    nextChoice = choice.next();
                    break;
            }
            return new Cart(uuid, nextX, nextY, nextDirection, nextChoice);
        }

        @Override
        public int compareTo(Cart o) {
            int row = Integer.compare(y, o.y);
            return row != 0 ? row : Integer.compare(x, o.x);
        }

        @Override
        public String toString() {
            return "Cart{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction='" + direction + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Cart cart = (Cart) o;
            return uuid.equals(cart.uuid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uuid);
        }
    }

    @SuppressWarnings("Duplicates")
    private enum Direction {
        UP, DOWN, LEFT, RIGHT;

        Direction left() {
            switch (this) {
                case UP:
                    return LEFT;
                case LEFT:
                    return DOWN;
                case DOWN:
                    return RIGHT;
                case RIGHT:
                    return UP;
            }
            throw new IllegalStateException();
        }

        Direction right() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;
            }
            throw new IllegalStateException();
        }
    }

    private enum Choice {
        LEFT, STRAIGHT, RIGHT;

        Choice next() {
            switch (this) {
                case LEFT:
                    return STRAIGHT;
                case STRAIGHT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
            }
            throw new IllegalStateException();
        }

        Direction choose(Direction direction) {
            switch (this) {
                case LEFT:
                    return direction.left();
                case STRAIGHT:
                    return direction;
                case RIGHT:
                    return direction.right();
            }
            throw new IllegalStateException();
        }
    }
}
