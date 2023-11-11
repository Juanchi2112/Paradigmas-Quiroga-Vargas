package linea;

import java.util.ArrayList;
import java.util.List;

public class Linea {

    public static final String InvalidDimensionsMessage = "Dimensiones inválidas";
    public static final String ColumnOutOfRangeMessage = "Columna fuera de rango";
    public static String FullColumnMessage = "Columna llena";

    private List<List<Character>> columns = new ArrayList<>();
    private int base;
    private int height;

    private int lastMoveRow;
    private int lastMoveColumn;

    private GameMode gameMode;
    private GameStatus gameStatus = new PlayingRed( this );

    public Linea( int base, int height, char gameModeKey ) {
        this.base = base;
        this.height = height;
        gameMode = GameMode.find( gameModeKey );
        if (base < 1 || height < 1) {
            throw new RuntimeException(InvalidDimensionsMessage);
        }
        for (int i = 0; i < base; i++) {
            columns.add( new ArrayList<>() );
        }
    }

    public Linea playRedAt( int column ) {
        gameStatus.playWithRed( column );
        processPlayerLastMove();
        return this;
    }

    public Linea playBlueAt( int column ) {
        gameStatus.playWithBlue( column );
        processPlayerLastMove();
        return this;
    }

    public void addPieceTo( int columnNumber ) {
        if (columnNumber < 1 || columnNumber > base) {
            throw new RuntimeException(ColumnOutOfRangeMessage);
        }
        List<Character> column = columns.get( columnNumber - 1 );
        if (column.size() < height) {
            column.add( gameStatus.associatedPiece() );
            lastMoveColumn = columnNumber;
            lastMoveRow = height - column.size() + 1;
        } else {
            throw new RuntimeException( FullColumnMessage );
        }
    }

    private void processPlayerLastMove() {
        gameMode.checkWin( this );
        checkDraw();
        gameStatus.nextTurn();
    }

    private void checkDraw() {
        if ( !win() && columns.stream().allMatch( column -> column.size() == height ) ) {
            gameStatus.finishWithDraw();
        }
    }

    public void checkColumnsAndRows() {
        check4ConsecutiveAroundLastMove( 1, 0);
        check4ConsecutiveAroundLastMove( 0, 1 );
    }

    public void checkAllDirections() {
        checkColumnsAndRows();
        checkDiagonals();
    }

    public void checkDiagonals() {
        check4ConsecutiveAroundLastMove(1, 1);
        check4ConsecutiveAroundLastMove( 1, -1);
    }

    private void check4ConsecutiveAroundLastMove( int rowChange, int colChange ) {
        int consecutiveCount = 0;
        for (int diff = -3; diff <= 3; diff++) {
            if ( pieceAt(lastMoveRow + diff * rowChange, lastMoveColumn + diff * colChange) == gameStatus.associatedPiece() ) {
                consecutiveCount++;
                if (consecutiveCount == 4) {
                    gameStatus.finishWithWin();
                }
            } else {
                consecutiveCount = 0;
            }
        }
    }

    public char pieceAt( int rowNumber, int columnNumber ) {
        try {
            return columns.get( columnNumber - 1 ).get( height - rowNumber );
        } catch (IndexOutOfBoundsException e) {
            return '-';
        }
    }

    public String show() {
        String result = gameStatus.associatedMessage() + "\n";
        for (int row = 1; row <= height; row++) {
            result += "| ";
            for (int column = 1; column <= base; column++) {
                result += pieceAt( row, column ) + " ";
            }
            result += "|\n";
        }
        return result;
    }

    public void setGameStatus( GameStatus gameStatus ) { this.gameStatus = gameStatus; }

    public boolean redWins() { return gameStatus instanceof RedWon; }
    public boolean blueWins() { return gameStatus instanceof BlueWon; }
    public boolean win() { return blueWins() || redWins(); }
    public boolean draw() { return gameStatus instanceof Draw; }
    public boolean finished() { return gameStatus instanceof NoOnePlaying; }

}