package nemo;

import java.util.Objects;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Coordinate add( Coordinate point ) {
        return new Coordinate( x + point.getX(), y + point.getY() );
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