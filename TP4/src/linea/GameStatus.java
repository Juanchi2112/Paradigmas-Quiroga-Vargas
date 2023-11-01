package linea;

public abstract class GameStatus {

    public static GameStatus playingRed() {
        return new PlayingRed();
    }

    public static GameStatus playingBlue() {
        return new PlayingBlue();
    }

    public static GameStatus gameFinished() {
        return new GameFinished();
    }

    public abstract void playWithRed( Linea game, int column );
    public abstract void playWithBlue( Linea game, int column );
    public abstract GameStatus next();

    public char colorPiece() {
        return ' ';
    }

}
