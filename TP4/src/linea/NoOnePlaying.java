package linea;

public class NoOnePlaying extends GameStatus {

    public static String ErrorMessage = "El juego ya terminó";


    public NoOnePlaying(Linea game, String message ) {
        super(game, message);
        teamName = "";
    }

    public void playWithRed( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void playWithBlue( int column ) {
        throw new RuntimeException( ErrorMessage );
    }

    public void next() {}

}