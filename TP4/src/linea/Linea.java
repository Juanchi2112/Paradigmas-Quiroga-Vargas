package linea;

import java.util.ArrayList;
import java.util.List;

public class Linea {

    public static String FullColumnMessage = "Columna llena";

    private int base;
    private int height;
    private List<List<Character>> columns = new ArrayList<>();

    private GameMode gameMode;
    private GameStatus gameStatus = new PlayingRed( this );

    private boolean redWon = false;
    private boolean blueWon = false;
    private boolean draw = false;

    public Linea( int base, int height, char gameModeKey ) {
        this.base = base;
        this.height = height;
        if (base < 1 || height < 1) {
            throw new RuntimeException( "Dimensiones inválidas" );
        }
        for (int i = 0; i < base; i++) {
            columns.add( new ArrayList<>() );
        }
        gameMode = GameMode.find( gameModeKey );
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
    
    public void addPieceTo( int columnIndex ) {
        if (columnIndex < 0 || columnIndex >= base) {
            throw new RuntimeException( "Columna fuera de rango" );
        }
        List<Character> column = columns.get( columnIndex );
        if (column.size() < height) {
            column.add( gameStatus.colorPiece() );
        } else {
            throw new RuntimeException( FullColumnMessage );
        }
    }

    private boolean check4ConsecutivePieces(int startRow, int startColumn, int rowDiff, int columnDiff) {
        for (int row = startRow; row <= height - rowDiff; row++) {
            for (int column = startColumn; column <= base - columnDiff; column++) {
                boolean consecutiveFound = true;
                for (int i = 0; i < 4; i++) {
                    if (pieceAt(row + i * rowDiff, column + i * columnDiff) != gameStatus.colorPiece()) {
                        consecutiveFound = false;
                        break;
                    }
                }
                if (consecutiveFound) {
                    gameStatus.finishGame();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkColumnsAndRows() {
        return check4ConsecutivePieces(1, 1, 0, 1)
                || check4ConsecutivePieces(1, 1, 1, 0);
    }

    public boolean checkDiagonals() {
        return check4ConsecutivePieces(4, 1, -1, 1)
                || check4ConsecutivePieces(1, 1, 1, 1);
    }

    public boolean checkAllDirections() {
        return checkColumnsAndRows() || checkDiagonals();
    }

    public char pieceAt(int rowNumber, int columnNumber ) {
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