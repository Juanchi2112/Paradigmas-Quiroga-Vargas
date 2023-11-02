package linea;

public abstract class GameStatus {
    
    protected Linea game;
    
    public GameStatus( Linea game ) {
        this.game = game;
    }
    
    public static GameStatus GameOver(Linea game ) {
        return new GameOver( game );
    }

    public abstract void playWithRed( int column );
    public abstract void playWithBlue( int column );
    public abstract void next();

    public void finishGame() {
        game.setGameStatus( GameOver( game ) );
    }

    public char colorPiece() {
        return ' ';
    }

}
