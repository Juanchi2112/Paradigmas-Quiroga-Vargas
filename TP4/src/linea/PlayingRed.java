package linea;

public class PlayingRed extends GameStatus {

    public static String ErrorMessage = "No es el turno de las azules";

    public PlayingRed(Linea game) {
        super(game);
    }

    public void playWithRed( int column ) {
        game.addPieceTo(column - 1);
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void next() {
        game.setGameStatus( new PlayingBlue( game ) );
    }

    public char colorPiece() {
        return 'R';
    }

}
