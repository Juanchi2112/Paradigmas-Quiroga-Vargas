package nemo;

public class DNorth extends Direction {
    public Point move(Point position) {
        return new Point(position.getX(), position.getY() + 1);
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
