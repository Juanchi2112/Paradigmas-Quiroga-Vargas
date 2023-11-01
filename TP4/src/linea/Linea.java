package linea;

import java.util.ArrayList;
import java.util.List;

public class Linea {

    public static String FullColumnMessage = "Columna llena";

    public int base;
    public int height;

    public GameMode gameMode;
    public GameStatus gameStatus = GameStatus.playingRed();
    public List<List<Character>> columns = new ArrayList<>();

    public boolean redWon = false;
    public boolean blueWon = false;
    public boolean draw = false;

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
        gameStatus.playWithRed( this, column );
        redWon = gameMode.checkWin( this );
        refreshDraw();
        gameStatus = gameStatus.next();
        return this;
    }

    public Linea playBlueAt( int column ) {
        gameStatus.playWithBlue( this, column );
        blueWon = gameMode.checkWin( this );
        refreshDraw();
        gameStatus = gameStatus.next();
        return this;
    }

    public void addPieceTo(int columnIndex ) {
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

    public boolean checkColumnsAndRows() {
        // Verificar filas
        for (int row = 1; row <= height; row++) {
            for (int column = 1; column <= base - 3; column++) {
                boolean consecutiveFound = true;
                for (int i = 0; i < 4; i++) {
                    if (pieceAt( row, column + i) != gameStatus.colorPiece() ) {
                        consecutiveFound = false;
                        break;
                    }
                }
                if (consecutiveFound) {
                    gameStatus = GameStatus.gameFinished();
                    return true;
                }
            }
        }

        // Verificar columnas
        for (int column = 1; column <= base; column++) {
            for (int row = 1; row <= height - 3; row++) {
                boolean consecutiveFound = true;
                for (int i = 0; i < 4; i++) {
                    if (pieceAt( row + i, column) != gameStatus.colorPiece()) {
                        consecutiveFound = false;
                        break;
                    }
                }
                if (consecutiveFound) {
                    gameStatus = GameStatus.gameFinished();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonals() {
        // Verificar diagonales ascendentes
        for (int row = 4; row <= height; row++) {
            for (int column = 1; column <= base - 3; column++) {
                boolean consecutiveFound = true;
                for (int i = 0; i < 4; i++) {
                    if (pieceAt(row - i, column + i ) != gameStatus.colorPiece()) {
                        consecutiveFound = false;
                        break;
                    }
                }
                if (consecutiveFound) {
                    gameStatus = GameStatus.gameFinished();
                    return true;
                }
            }
        }

        // Verificar diagonales descendentes
        for (int row = 1; row <= height - 3; row++) {
            for (int column = 1; column <= base - 3; column++) {
                boolean consecutiveFound = true;
                for (int i = 0; i < 4; i++) {
                    if (pieceAt( row+i, column+i) != gameStatus.colorPiece()) {
                        consecutiveFound = false;
                        break;
                    }
                }
                if (consecutiveFound) {
                    gameStatus = GameStatus.gameFinished();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkAllDirections() {
        return checkColumnsAndRows() || checkDiagonals();
    }

    public void refreshDraw() {
        draw = !win() && columns.stream().allMatch( column -> column.size() == height );
        if (draw) {
            gameStatus = GameStatus.gameFinished();
        }
    }

    public boolean redWins() {
        return redWon;
    }

    public boolean blueWins() {
        return blueWon;
    }

    public boolean win() {
        return blueWins() || redWins();
    }

    public boolean draw() {
        return draw;
    }
    public boolean finished() {
        return draw()||win();
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

    public void setGameStatus( GameStatus gameStatus ) {
        this.gameStatus = gameStatus;
    }

}
