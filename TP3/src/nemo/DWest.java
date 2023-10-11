package nemo;

public class DWest extends Direction {
    public Point move(Point position) {
        return new Point(position.getX() - 1, position.getY());
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
