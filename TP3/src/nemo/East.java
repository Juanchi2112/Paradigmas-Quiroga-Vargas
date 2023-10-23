package nemo;

public class East extends Direction {

    public Coordinate differential() {
        return new Coordinate(1, 0);
    }

    public Direction right() {
        return Direction.South();
    }

    public Direction left() {
        return Direction.North();
    }

}