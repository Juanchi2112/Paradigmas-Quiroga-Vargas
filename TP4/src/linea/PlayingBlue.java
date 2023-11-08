package linea;

public class PlayingBlue extends GameStatus {

    public static String ErrorMessage = "No es el turno de las rojas";

    public PlayingBlue( Linea game ) {
        super(game);
        associatedMessage = "Juegan las Azules";
        associatedPiece = 'B';
    }

    public void playWithRed( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue( int column ) {
        game.addPieceTo( column );
    }

    public void nextTurn() {
        game.setGameStatus( new PlayingRed( game ) );
    }

    public void finishWithWin() {
        game.setGameStatus( new BlueWon( game ) );
    }

}
