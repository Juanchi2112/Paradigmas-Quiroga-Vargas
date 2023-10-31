package lineaPruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Linea {

    public int base;
    public int height;
    public int winVariant;
    public int[][] board;

    public Linea( int base, int height, char winVariant ) {
        this.base = base;
        this.height = height;
        board = new int[height][base];
        this.winVariant = winVariant;
    }

    public void playRedAt( int column ) {
        column -=1;
        int row = height - 1;
        while (row >= 0 && board[row][column] != 0) {
            row--;
        }
        if (row >= 0) {
            board[row][column] = 1;
        }

    }

    public void playBlueAt( int column ) {
        column -= 1;
        int row = height - 1;
        while (row >= 0 && board[row][column] != 0) {
            row--;
        }

        if (row >= 0) {
            board[row][column] = -1;
        }

    }

    public List<Integer> sumaFilas() {
        List<Integer> sumas = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            int suma = 0;
            for (int column = 0; column < base; column++) {
                suma += board[row][column];
            }
            sumas.add(suma);
        }
        return sumas;
    }

    public List<Integer> sumaColumnas() {
        List<Integer> sumas = new ArrayList<>();
        for (int column = 0; column < base; column++) {
            int suma = 0;
            for (int row = 0; row < height; row++) {
                suma += board[row][column];
            }
            sumas.add(suma);
        }
        return sumas;
    }

    public boolean redWins() {
        return sumaFilas().contains(4) || sumaColumnas().contains(4);
    }

    public boolean blueWins() {
        return sumaFilas().contains(-4) || sumaColumnas().contains(-4);
    }

    public boolean win() {
        return blueWins() || redWins();
    }
    public boolean draw() {
        return !win() && Arrays.stream(board).flatMapToInt(Arrays::stream).noneMatch(i -> i == 0);
    }
    public boolean finished() {
        return draw()||win();
    }


        public String show() {
            String result = "";
            for (int row = 0; row < height; row++) {
                result += "|";
                result += " ";
                for (int column = 0; column < base; column++) {
                    result += board[row][column] == 0 ? " " : board[row][column] == 1 ? "R" : "B";
                    result += " ";
                }
                result += "|\n";
            }
            return result;
        }


}
