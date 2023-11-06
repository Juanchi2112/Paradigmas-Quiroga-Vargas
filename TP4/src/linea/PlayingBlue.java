package linea;

public class PlayingBlue extends GameStatus {

    public static String ErrorMessage = "No es el turno de las rojas";

    public PlayingBlue( Linea game, String message ) {
        super(game, message);
        teamName = "Azules";
    }

    public void playWithRed( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue( int column ) {
        game.addPieceTo( column );
    }

    public void next() {
        game.setGameStatus( defaultPlayingRed( game ) );
    }

    public char associatedPiece() {
        return 'B';
    }

}
