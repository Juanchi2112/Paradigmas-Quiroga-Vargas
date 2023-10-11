package nemo;

public class DSouth extends Direction {
    public Point move(Point position) {
        return new Point(position.getX(), position.getY() - 1);
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
