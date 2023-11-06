package linea;

import java.util.List;
import java.util.function.Consumer;

public class GameMode {

    private char key;
    private Consumer<Linea> checkWin;

    public static List<GameMode>  gameModes = List.of(
            new GameMode('A', Linea::checkColumnsAndRows),
            new GameMode('B', Linea::checkDiagonals),
            new GameMode('C', Linea::checkAllDirections)
            );

    public GameMode( char key, Consumer<Linea> checkWin ) {
        this.key = key;
        this.checkWin = checkWin;
    }

    public boolean isRepresentedBy( char key ) {
        return this.key == key;
    }

    public static GameMode find( char key ) {
        return gameModes.stream()
            .filter( gameMode -> gameMode.isRepresentedBy(key) )
            .findFirst()
            .get();
    }

    public void checkWin( Linea game ) {
        checkWin.accept( game );
    }

}
