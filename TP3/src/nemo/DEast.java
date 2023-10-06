package nemo;

public class DEast extends Direction {
    public Position move(Position position) {
        return new Position(position.getX() + 1, position.getY());
    }

    public Direction right() {
        return Direction.South();
    }

    public Direction left() {
        return Direction.North();
    }

    public String toString() {
        return "East";
    }

}
