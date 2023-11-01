package linea;

public class GameFinished extends GameStatus {

    public static String ErrorMessage = "El juego ya terminó";

    public void playWithRed(Linea game, int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue(Linea game, int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public GameStatus next() {
        return gameFinished();
    }

}