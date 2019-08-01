package pacman;

public enum Direction {
    UP, DOWN, RIGHT, LEFT;

    public Direction getOpposto() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
        }
        throw new UnsupportedOperationException();
    }

    public boolean isOpposto(Direction dir) {
        return getOpposto().equals(dir);
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public boolean isHorizontal() {
        return this == UP || this == DOWN;
    }
}

