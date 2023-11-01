package linea;

public class PlayingBlue extends GameStatus {
    public static String ErrorMessage = "No es el turno de las rojas";

    public void playWithRed(Linea game, int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue(Linea game, int column ) {
        game.addPieceTo( column - 1 );
    }

    public GameStatus next() {
        return playingRed();
    }

    public char colorPiece() {
        return 'B';
    }

}
