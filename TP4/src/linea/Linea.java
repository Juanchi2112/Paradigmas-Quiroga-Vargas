package linea;

import java.util.ArrayList;
import java.util.List;

public class Linea {

    public static String FullColumnMessage = "Columna llena";

    private List<List<Character>> columns = new ArrayList<>();
    private int base;
    private int height;
    private int lastMoveRow;
    private int lastMoveColumn;

    private GameMode gameMode;
    private GameStatus gameStatus = new PlayingRed( this );

    private boolean redWon = false;
    private boolean blueWon = false;
    private boolean draw = false;

    public Linea( int base, int height, char gameModeKey ) {
        this.base = base;
        this.height = height;
        gameMode = GameMode.find( gameModeKey );
        if (base < 1 || height < 1) {
            throw new RuntimeException( "Dimensiones inválidas" );
        }
        for (int i = 0; i < base; i++) {
            columns.add( new ArrayList<>() );
        }
    }

    public Linea playRedAt( int column ) {
        gameStatus.playWithRed( column );
        redWon = processPlayerLastMove();
        return this;
    }

    public Linea playBlueAt( int column ) {
        gameStatus.playWithBlue( column );
        blueWon = processPlayerLastMove();
        return this;
    }

    private boolean processPlayerLastMove() {
        boolean playerWon = gameMode.checkWin( this );
        checkDraw();
        gameStatus.next();
        return playerWon;
    }

    private void checkDraw() {
        draw = !win() && columns.stream().allMatch( column -> column.size() == height );
        if (draw) {
            gameStatus.finishGame();
        }
    }

    public void addPieceTo( int columnNumber ) {
        if (columnNumber < 1 || columnNumber > base) {
            throw new RuntimeException( "Columna fuera de rango" );
        }
        List<Character> column = columns.get( columnNumber - 1 );
        if (column.size() < height) {
            column.add( gameStatus.colorPiece() );
            lastMoveColumn = columnNumber;
            lastMoveRow = height - column.size() + 1;
        } else {
            throw new RuntimeException( FullColumnMessage );
        }
    }

    private boolean check4ConsecutiveAroundLastMove( int rowChange, int colChange ) {
        int consecutiveCount = 0;
        for (int diff = -3; diff <= 3; diff++) {
            if (pieceAt(lastMoveRow + diff * rowChange, lastMoveColumn + diff * colChange) == gameStatus.colorPiece()) {
                consecutiveCount++;
                if (consecutiveCount == 4) {
                    gameStatus.finishGame();
                    return true;
                }
            } else {
                consecutiveCount = 0;
            }
        }
        return false;
    }

    public boolean checkColumnsAndRows() {
        return check4ConsecutiveAroundLastMove( 1, 0) || check4ConsecutiveAroundLastMove( 0, 1 );
    }

    public boolean checkDiagonals() {
        return check4ConsecutiveAroundLastMove(1, 1)  || check4ConsecutiveAroundLastMove( 1, -1);
    }

    public boolean checkAllDirections() {
        return checkColumnsAndRows() || checkDiagonals();
    }

    public char pieceAt( int rowNumber, int columnNumber ) {
        try {
            return columns.get( columnNumber - 1 ).get( height - rowNumber );
        } catch (IndexOutOfBoundsException e) {
            return '-';
        }
    }

    public String show() {
        String result = "";
        for (int row = 1; row <= height; row++) {
            result += "|";
            result += " ";
            for (int column = 1; column <= base; column++) {
                result += pieceAt( row, column );
                result += " ";
            }
            result += "|\n";
        }
        return result;
    }

    public void setGameStatus( GameStatus gameStatus ) { this.gameStatus = gameStatus; }

    public boolean redWins() { return redWon; }
    public boolean blueWins() { return blueWon; }
    public boolean win() { return blueWins() || redWins(); }
    public boolean draw() { return draw; }
    public boolean finished() { return draw() || win(); }

}