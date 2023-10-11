package nemo;

public class DEast extends Direction {
    public Point move(Point position) {
        return new Point(position.getX() + 1, position.getY());
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
