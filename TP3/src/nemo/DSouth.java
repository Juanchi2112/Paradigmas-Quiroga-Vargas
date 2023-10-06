package nemo;

public class DSouth extends Direction {
    public Position move(Position position) {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Direction right() {
        return Direction.West();
    }

    public Direction left() {
        return Direction.East();
    }

    public String toString() {
        return "South";
    }

}
