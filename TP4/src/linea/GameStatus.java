package linea;

public abstract class GameStatus {

    public static String NotRedTurnMessage = "No es el turno de las rojas";
    public static String NotBlueTurnMessage = "No es el turno de las azules";
    public static String FinishedGameMessage = "El juego ya terminó";
    
    protected Linea game;
    protected String associatedMessage;
    protected char associatedPiece;
    
    public GameStatus( Linea game ) {
        this.game = game;
    }

    public abstract void playWithRed( int column );
    public abstract void playWithBlue( int column );
    public abstract void nextTurn();

    public abstract void finishWithWin();
    public void finishWithDraw() { game.setGameStatus( new Draw( game ) ); }

    public String associatedMessage() { return associatedMessage; }
    public char associatedPiece() { return associatedPiece; }

}
