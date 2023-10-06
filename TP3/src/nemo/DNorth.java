package nemo;

public class DNorth extends Direction {
    public Position move(Position position) {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Direction right() {
        return Direction.East();
    }
    public Direction left() {
        return Direction.West();
    }

    public String toString() {
        return "North";
    }
}
