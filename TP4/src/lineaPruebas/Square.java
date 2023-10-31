package lineaPruebas;

public abstract class Square {

    public static Square Red() {
        return new RedSquare();
    }

    public static Square Blue() {
        return new BlueSquare();
    }

    public static Square Empty() {
        return new EmptySquare();
    }

    public abstract char show();


}
