package linea;

public class PlayingRed extends GameStatus {

    public static String ErrorMessage = "No es el turno de las azules";

    public PlayingRed(Linea game, String message) {
        super(game, message);
        teamName = "Rojas";
    }

    public void playWithRed( int column ) {
        game.addPieceTo(column);
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void next() {
        game.setGameStatus( defaultPlayingBlue( game ) );
    }

    public char associatedPiece() {
        return 'R';
    }

}
