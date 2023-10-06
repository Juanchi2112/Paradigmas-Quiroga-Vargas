package nemo;

import java.util.Objects;

public class Position {
    public int x;
    public int y;

    public Position( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
        return Objects.hash( x, y );
    }

    public boolean equals( Object obj ) {
        return this == obj ||
                ( obj != null &&
                        getClass() == obj.getClass() &&
                        x == ( ((Position)obj).x ) &&
                        y == ( ((Position)obj).y));
    }

    public int getX() { return x; }
    public int getY() { return y; }
}


