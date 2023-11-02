package linea;

public class GameOver extends GameStatus {

    public static String ErrorMessage = "El juego ya terminó";

    public GameOver(Linea game) {
        super(game);
    }

    public void playWithRed( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void next() {}

}