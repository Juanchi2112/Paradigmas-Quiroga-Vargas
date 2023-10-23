package nemo;

public class South extends Direction {

    public Coordinate differential() {
        return new Coordinate(0, -1);
    }

    public Direction right() {
        return Direction.West();
    }

    public Direction left() {
        return Direction.East();
    }

}