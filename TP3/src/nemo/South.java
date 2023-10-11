package nemo;

public class South extends Direction {
    public Coordinate addOneInThisDirection(Coordinate coordinate ) {
        return coordinate.add(0, -1 );
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
