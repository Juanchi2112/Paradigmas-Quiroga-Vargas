package linea;

public class PlayingRed extends GameStatus {
    public static String ErrorMessage = "No es el turno de las azules";

    public void playWithRed(Linea game, int column ) {
        game.addPieceTo(column - 1);
    }

    public void playWithBlue(Linea game, int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public GameStatus next() {
        return playingBlue();
    }

    public char colorPiece() {
        return 'R';
    }

}
