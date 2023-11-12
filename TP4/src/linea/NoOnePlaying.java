package linea;

public class NoOnePlaying extends GameStatus {

    public NoOnePlaying(Linea game) {
        super(game);
        associatedPiece = ' ';
    }

    public void playWithRed( int column ) {
        throw new RuntimeException( FinishedGameMessage );
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( FinishedGameMessage );
    }

    public void finishWithWin() {}
    public void setNextTurn() {}

}