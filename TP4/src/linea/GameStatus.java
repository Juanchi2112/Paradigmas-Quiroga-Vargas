package linea;

public abstract class GameStatus {
    
    protected Linea game;
    protected String message;
    protected String teamName;

    
    public GameStatus( Linea game, String message ) {
        this.game = game;
        this.message = message;
    }

    public static GameStatus defaultPlayingRed( Linea game ) {
        return new PlayingRed( game, "Juegan las rojas" );
    }

    public static GameStatus defaultPlayingBlue( Linea game ) {
        return new PlayingBlue( game, "Juegan las azules" );
    }

    public static GameStatus draw( Linea game ) {
        return new NoOnePlaying( game, "Resultado Final: " + "Empate");
    }

    public abstract void playWithRed( int column );
    public abstract void playWithBlue( int column );
    public abstract void next();

    public void finishGame( String finalResult ) {
        game.setGameStatus( new NoOnePlaying( game, finalResult ) );
    }

    public boolean equals( Object obj ) {
        return obj == this ||
                (obj.getClass() == this.getClass() &&
                        this.game == ((GameStatus) obj).game &&
                        this.message.equals( ((GameStatus) obj).message) );
    }

    public char associatedPiece() {
        return ' ';
    }
    public String associatedMessage() {
        return message;
    }
    public String teamName() {
        return teamName;
    }

}
