package nemo;

public class North extends Direction {

    public Coordinate differential() {
        return new Coordinate(0, 1);
    }

    public Direction right() {
        return Direction.East();
    }

    public Direction left() {
        return Direction.West();
    }

}