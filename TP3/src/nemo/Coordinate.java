package nemo;

import java.util.Objects;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Coordinate add( int x, int y ) {
        return new Coordinate( getX() + x, getY() + y );
    }

    public int hashCode() {
        return Objects.hash( x, y );
    }

    public boolean equals( Object obj ) {
        return this == obj ||
                ( obj != null &&
                        getClass() == obj.getClass() &&
                        x == ( ((Coordinate)obj).x ) &&
                        y == ( ((Coordinate)obj).y));
    }

    public int getX() { return x; }
    public int getY() { return y; }
}


