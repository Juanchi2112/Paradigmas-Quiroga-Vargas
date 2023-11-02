package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTest {

    @Test public void testEmpty7x6Board() {
        Linea game = new Linea( 7, 6, 'A' );
        assertFalse( game.finished() );
    }

    @Test public void testInvalidDimensions() {
        assertThrowsLike( () -> new Linea( 0, -2, 'A' ), "Dimensiones inválidas" );
    }

    @Test public void testRedPlaysFirst() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        assertEquals( 'R', game.pieceAt(6,1) );
    }

    @Test public void testColumnOutOfRange() {
        Linea game = new Linea( 7, 6, 'A' );
        assertThrowsLike( () -> game.playRedAt(8), "Columna fuera de rango" );
    }

    @Test public void testColumnOutOfRange2() {
        Linea game = new Linea( 7, 6, 'A' );
        assertThrowsLike( () -> game.playRedAt(0), "Columna fuera de rango" );
    }

    @Test public void testRedPlaysTwice() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        assertThrowsLike( () -> game.playRedAt(1), "No es el turno de las rojas" );
    }

    @Test public void testBluePlaysFirst() {
        Linea game = new Linea( 7, 6, 'A' );
        assertThrowsLike( () -> game.playBlueAt(1), "No es el turno de las azules" );
    }

    @Test public void testBluePlaysSecond() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        game.playBlueAt( 1 );
        assertEquals( 'B', game.pieceAt(5, 1 ) );
    }

    @Test public void testBluePlaysTwice() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        game.playBlueAt( 1 );
        assertThrowsLike( () -> game.playBlueAt(1), "No es el turno de las azules" );
    }

    @Test public void testTurnsAlternateCorrectly() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2 );
        game.playRedAt(2 );
        game.playBlueAt(2);

        assertEquals( game.pieceAt(4, 1), 'R' );
        assertEquals( game.pieceAt(4, 2), 'B' );
    }

    @Test public void testTurnsAlternateCorrectly2() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2 );

        game.playRedAt(2 );
        assertThrowsLike( () -> game.playRedAt(2), "No es el turno de las rojas" );

        game.playBlueAt(2);
        assertThrowsLike( () -> game.playBlueAt(2), "No es el turno de las azules" );
    }

    @Test public void testFullColumn() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(1);
        assertThrowsLike( () -> game.playRedAt(1), "Columna llena" );
    }

    @Test public void testRedWinsWith4InARow() {
        Linea game = new Linea( 7, 6, 'A');
        game.playRedAt(1).playBlueAt(1);
        game.playRedAt(2).playBlueAt(2);
        game.playRedAt(3).playBlueAt(3);
        game.playRedAt(4);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testBlueWinsWith4InARow() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(5).playBlueAt(1);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(2).playBlueAt(3);
        game.playRedAt(3).playBlueAt( 4 );

        assertTrue( game.blueWins() );
        assertTrue( game.finished() );
    }

    @Test public void testRedWinsWith4InAColumn() {
        Linea game = new Linea( 7, 6, 'A' );
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testBlueWinsWith4InAColumn() {
        Linea game = new Linea(7, 6, 'A');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(3).playBlueAt(2);

        assertTrue(game.blueWins());
        assertTrue(game.finished());
    }

    @Test public void testPlayerDoesNotWinWith4InADiagonalInVariantA() {
        Linea game = new Linea(7, 6, 'A');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(2).playBlueAt(3);
        game.playRedAt(4).playBlueAt(3);
        game.playRedAt(3).playBlueAt(4);
        game.playRedAt(5).playBlueAt(4);
        game.playRedAt(4);

        assertEquals(game.pieceAt(3, 4), 'R');
        assertFalse(game.win());
        assertFalse(game.finished());

    }

    @Test public void testRedWinsWith4InADiagonalInVariantB() {
        Linea game = new Linea(7, 6, 'B');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(2).playBlueAt(3);
        game.playRedAt(4).playBlueAt(3);
        game.playRedAt(3).playBlueAt(4);
        game.playRedAt(5).playBlueAt(4);
        game.playRedAt(4);

        assertEquals(game.pieceAt(3, 4), 'R');
        assertTrue(game.redWins());
        assertTrue(game.finished());
    }

    @Test public void testBlueWinsWith4InADiagonalInVariantB() {
        Linea game = new Linea(7, 6, 'B');
        game.playRedAt(6).playBlueAt(1);
        game.playRedAt(2).playBlueAt(2);
        game.playRedAt(3).playBlueAt(4);
        game.playRedAt(3).playBlueAt(3);
        game.playRedAt(4).playBlueAt(5);
        game.playRedAt(4).playBlueAt(4);


        assertEquals(game.pieceAt(3, 4), 'B');
        assertTrue(game.blueWins());
        assertTrue(game.finished());
    }

    @Test public void testPlayerDoesNotWinWith4InAColumnOrRowInVariantB() {
        Linea game = new Linea(7, 6, 'B');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(3);
        game.playRedAt(1).playBlueAt(4);
        game.playRedAt(1);

        assertFalse(game.redWins());

        game.playBlueAt(5);
        assertFalse(game.blueWins());
        assertFalse(game.finished());
    }

    @Test public void testPlayerWinsWith4InARowInVariantC() {
        Linea game = new Linea( 7, 6, 'C');
        game.playRedAt(1).playBlueAt(1);
        game.playRedAt(2).playBlueAt(2);
        game.playRedAt(3).playBlueAt(3);
        game.playRedAt(4);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testPlayerWinsWith4InAColumnInVariantC() {
        Linea game = new Linea( 7, 6, 'C');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(1);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testPlayerWinsWith4InADiagonalInVariantC() {
        Linea game = new Linea( 7, 6, 'C');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(2).playBlueAt(3);
        game.playRedAt(4).playBlueAt(3);
        game.playRedAt(3).playBlueAt(4);
        game.playRedAt(5).playBlueAt(4);
        game.playRedAt(4);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testGameIsFinishedAfterAWinAndItIsNotPosibleToContinuePlaying() {
        Linea game = new Linea( 7, 6, 'A');
        game.playRedAt(1).playBlueAt(1);
        game.playRedAt(2).playBlueAt(2);
        game.playRedAt(3).playBlueAt(3);
        game.playRedAt(4);

        assertTrue( game.win() );
        assertTrue( game.finished() );
        assertThrowsLike( () -> game.playRedAt(1), "El juego ya terminó" );
    }

    @Test public void testGameIsFinishedAfterADrawAndItIsNotPosibleToContinuePlaying() {
        Linea game = new Linea( 5, 4, 'A');
        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(3).playBlueAt(4);
        game.playRedAt(5).playBlueAt(1);
        game.playRedAt(2).playBlueAt(3);
        game.playRedAt(4).playBlueAt(5);

        game.playRedAt(1).playBlueAt(2);
        game.playRedAt(3).playBlueAt(4);
        game.playRedAt(5).playBlueAt(1);
        game.playRedAt(2).playBlueAt(3);
        game.playRedAt(4).playBlueAt(5);

        assertTrue( game.draw() );
        assertTrue( game.finished() );
        assertThrowsLike( () -> game.playRedAt(1), "El juego ya terminó" );

    }

    //Test en el que se gana en la ultima jugada antes del empate, debe tomarlo como victoria y no como empate.
//    @Test public void testWinOneMoveBeforeADraw() {
//    }

    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( assertThrows( RuntimeException.class, executable).getMessage(), message );
    }

}