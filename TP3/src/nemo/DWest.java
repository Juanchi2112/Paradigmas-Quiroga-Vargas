package nemo;

public class DWest extends Direction {
    public Position move(Position position) {
        return new Position(position.getX() - 1, position.getY());
    }

    public Direction right() {
        return Direction.North();
    }

    public Direction left() {
        return Direction.South();
    }

    public String toString() {
        return "West";
    }

}
