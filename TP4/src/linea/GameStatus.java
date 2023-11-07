package linea;

public abstract class GameStatus {
    
    protected Linea game;
    protected String associatedMessage;
    
    public GameStatus( Linea game ) {
        this.game = game;
    }

    public abstract void playWithRed( int column );
    public abstract void playWithBlue( int column );
    public abstract void next();
    public abstract void finishWithWin();

    public void finishWithDraw() {
        game.setGameStatus( new Draw( game ) );
    }

    public char associatedPiece() {
        return ' ';
    }
    public String associatedMessage() {
        return associatedMessage;
    }


}
