package nemo;

public class West extends Direction {
    public Coordinate addOneInThisDirection(Coordinate coordinate ) {
        return coordinate.add(-1, 0 );
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
