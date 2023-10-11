package nemo;

public class East extends Direction {
    public Coordinate addOneInThisDirection(Coordinate coordinate ) {
        return coordinate.add( 1, 0 );
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
