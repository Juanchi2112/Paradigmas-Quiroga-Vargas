package linea;

public class PlayingRed extends GameStatus {

    public static String ErrorMessage = "No es el turno de las azules";

    public PlayingRed(Linea game) {
        super(game);
        associatedMessage = "Juegan las Rojas";
        associatedPiece = 'R';
    }

    public void playWithRed( int column ) {
        game.addPieceTo(column);
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void nextTurn() {
        game.setGameStatus( new PlayingBlue( game ) );
    }

    public void finishWithWin() {
        game.setGameStatus( new RedWon( game ) );
    }

}
