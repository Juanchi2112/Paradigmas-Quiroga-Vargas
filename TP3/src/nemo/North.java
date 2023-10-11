package nemo;

public class North extends Direction {
    public Coordinate addOneInThisDirection(Coordinate coordinate ) {
        return coordinate.add( 0, 1 );
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
