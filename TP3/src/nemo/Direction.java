package nemo;

public abstract class Direction {
    public static Direction North() {
        return new DNorth();
    }
    public static Direction South() {
        return new DSouth();
    }
    public static Direction East() {
        return new DEast();
    }
    public static Direction West() {
        return new DWest();
    }
    public abstract Point move(Point position);
    public abstract Direction right();
    public abstract Direction left();
    public abstract String toString();

}
