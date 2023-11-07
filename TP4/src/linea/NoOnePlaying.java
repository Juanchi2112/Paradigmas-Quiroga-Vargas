package linea;

public class NoOnePlaying extends GameStatus {

    public static String ErrorMessage = "El juego ya terminó";

    public NoOnePlaying(Linea game) {
        super(game);
    }

    public void playWithRed( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void finishWithWin() {}
    public void next() {}

}