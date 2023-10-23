package nemo;

public class West extends Direction {

    public Coordinate differential() {
        return new Coordinate(-1, 0);
    }

    public Direction right() {
        return Direction.North();
    }

    public Direction left() {
        return Direction.South();
    }

}