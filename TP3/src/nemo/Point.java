package nemo;

import java.util.Objects;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Point add(Point other ) {
        return new Point( x + other.x, y + other.y );
    }

    public int hashCode() {
        return Objects.hash( x, y );
    }

    public boolean equals( Object obj ) {
        return this == obj ||
                ( obj != null &&
                        getClass() == obj.getClass() &&
                        x == ( ((Point)obj).x ) &&
                        y == ( ((Point)obj).y));
    }

    public int getX() { return x; }
    public int getY() { return y; }
}


