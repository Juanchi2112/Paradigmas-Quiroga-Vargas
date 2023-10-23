package nemo;

public abstract class Direction {

    public static Direction North() {
        return new North();
    }

    public static Direction South() {
        return new South();
    }

    public static Direction West() {
        return new West();
    }

    public static Direction East() {
        return new East();
    }

    public boolean equals( Object other ) {
        return other.getClass().equals( this.getClass() );
    }

    public abstract Coordinate differential();
    public abstract Direction right();
    public abstract Direction left();

}