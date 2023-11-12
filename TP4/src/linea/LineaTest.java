package linea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineaTest {

    protected Linea defaultGameA;

    @BeforeEach public void setUp() {
        defaultGameA = new Linea( 7, 6, 'A' );
    }

    @Test public void testEmpty7x6Board() {
        Linea game = new Linea( 7, 6, 'A' );
        assertFalse( game.finished() );
    }

    @Test public void testInvalidDimensions() {
        assertThrowsLike( () -> new Linea( 0, -2, 'A' ), Linea.InvalidDimensionsMessage );
    }

    @Test public void testInvalidGameMode() {
        assertThrowsLike( () -> new Linea( 7, 6, 'D' ), GameMode.InvalidGameMode );
    }

    @Test public void testRedPlaysFirst() {
        assertEquals( 'R', defaultGameA.playRedAt(1).pieceAt(6,1) );
    }

    @Test public void testColumnOutOfRange() {
        assertThrowsLike( () -> defaultGameA.playRedAt(8), Linea.ColumnOutOfRangeMessage );
    }

    @Test public void testColumnOutOfRange2() {
        assertThrowsLike( () -> defaultGameA.playRedAt(0), Linea.ColumnOutOfRangeMessage );
    }

    @Test public void testRedPlaysTwice() {
        assertThrowsLike( () -> defaultGameA.playRedAt(1).playRedAt( 1 ), GameStatus.NotRedTurnMessage);
    }

    @Test public void testBluePlaysFirst() {
        assertThrowsLike( () -> defaultGameA.playBlueAt(1), GameStatus.NotBlueTurnMessage);
    }

    @Test public void testBluePlaysSecond() {
        defaultGameA.playRedAt(1).playBlueAt( 1 );
        assertEquals( 'B', defaultGameA.pieceAt(5, 1 ) );
    }

    @Test public void testBluePlaysTwice() {
        defaultGameA.playRedAt(1).playBlueAt( 1 );
        assertThrowsLike( () -> defaultGameA.playBlueAt(1), GameStatus.NotBlueTurnMessage);
    }

    @Test public void testTurnsAlternateCorrectly() {
        playSequenceOfMoves( defaultGameA, 1, 1, 1, 2, 2, 2);

        assertEquals( defaultGameA.pieceAt(4, 1), 'R' );
        assertEquals( defaultGameA.pieceAt(4, 2), 'B' );
    }

    @Test public void testTurnsAlternateCorrectly2() {
        playSequenceOfMoves( defaultGameA, 1, 1, 1, 2, 2);
        assertThrowsLike( () -> defaultGameA.playRedAt(2), GameStatus.NotRedTurnMessage);

        defaultGameA.playBlueAt(2);
        assertThrowsLike( () -> defaultGameA.playBlueAt(2), GameStatus.NotBlueTurnMessage);
    }

    @Test public void testFullColumn() {
        playSequenceOfMoves(defaultGameA,1, 1, 1, 1, 1, 1 );
        assertThrowsLike( () -> defaultGameA.playRedAt(1), Linea.FullColumnMessage );
    }

    @Test public void testRedWinsWith4InARow() {
        movesToRowWinWithRed(defaultGameA);

        assertTrue( defaultGameA.redWins() );
        assertTrue( defaultGameA.finished() );
    }

    @Test public void testBlueWinsWith4InARow() {
        playSequenceOfMoves(defaultGameA, 5, 1, 1, 2, 2, 3, 3, 4) ;

        assertTrue( defaultGameA.blueWins() );
        assertTrue( defaultGameA.finished() );
    }

    @Test public void testRedWinsWith4InAColumn() {
        movesToVerticalWinWithRed(defaultGameA);

        assertTrue( defaultGameA.redWins() );
        assertTrue( defaultGameA.finished() );
    }

    @Test public void testBlueWinsWith4InAColumn() {
        playSequenceOfMoves(defaultGameA, 1, 2, 1, 2, 1, 2, 3, 2 );

        assertTrue(defaultGameA.blueWins());
        assertTrue(defaultGameA.finished());
    }

    @Test public void testPlayerDoesNotWinWith4InADiagonalInVariantA() {
        movesToDiagonalWinWithRed(defaultGameA);
        assertEquals( defaultGameA.pieceAt(3, 4), 'R' );
        assertFalse( defaultGameA.win());
        assertFalse( defaultGameA.finished() );

    }

    @Test public void testRedWinsWith4InADiagonalInVariantB() {
        Linea game = defaultGameB();
        movesToDiagonalWinWithRed(game);

        assertEquals(game.pieceAt(3, 4), 'R');
        assertTrue(game.redWins());
        assertTrue(game.finished());
    }

    @Test public void testBlueWinsWith4InADiagonalInVariantB() {
        Linea game = defaultGameB();
        playSequenceOfMoves( game, 6, 1, 2, 2, 3, 4, 3, 3, 4, 5, 4, 4);

        assertEquals(game.pieceAt(3, 4), 'B');
        assertTrue(game.blueWins());
        assertTrue(game.finished());
    }

    @Test public void testPlayerDoesNotWinWith4InAColumnOrRowInVariantB() {
        Linea game = defaultGameB();
        playSequenceOfMoves( game, 1, 2, 1, 2, 1, 4, 1);
        assertFalse(game.redWins());

        game.playBlueAt(5);
        assertFalse(game.blueWins());
        assertFalse(game.finished());
    }

    @Test public void testPlayerWinsWith4InARowInVariantC() {
        Linea game = defaultGameC();
        movesToRowWinWithRed(game);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testPlayerWinsWith4InAColumnInVariantC() {
        Linea game = defaultGameC();
        movesToVerticalWinWithRed(game);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testPlayerWinsWith4InADiagonalInVariantC() {
        Linea game = defaultGameC();
        movesToDiagonalWinWithRed(game);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testGameIsFinishedAfterAWinAndItIsNotPossibleToContinuePlaying() {
        playSequenceOfMoves( defaultGameA, 1, 1, 2, 2, 3, 3, 4 );

        assertTrue( defaultGameA.win() );
        assertTrue( defaultGameA.finished() );
        assertThrowsLike( () -> defaultGameA.playRedAt(1), GameStatus.FinishedGameMessage );
    }

    @Test public void testGameIsFinishedAfterADrawAndItIsNotPossibleToContinuePlaying() {
        Linea game = new Linea( 5, 4, 'A');
        playSequenceOfMoves( game, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 );

        assertTrue( game.draw() );
        assertTrue( game.finished() );
        assertThrowsLike( () -> game.playRedAt(1), GameStatus.FinishedGameMessage );

    }

    @Test public void testWinInTheMoveThatCompletesTheBoard() {
        Linea game = new Linea( 4, 4, 'C');
        playSequenceOfMoves( game,2, 1, 1, 2, 3, 3, 4, 3, 1, 4, 1, 4, 2, 2, 3, 4 );

        assertFalse( game.draw() );
        assertTrue(game.blueWins());
        assertTrue( game.finished() );
    }

    @Test public void testEmptyBoardShow(){
        assertEquals(defaultGameA.show(), "Juegan las Rojas\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n");
    }
    @Test public void testFinishedGameShow(){
        movesToRowWinWithRed(defaultGameA);
        assertEquals(defaultGameA.show(), "Ganaron las Rojas\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| - - - - - - - |\n" +
                "| B B B - - - - |\n" +
                "| R R R R - - - |\n");

    }
    @Test public void testCompleteBoardShow(){
        Linea game = new Linea( 5, 4, 'A');
        playSequenceOfMoves( game, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 );
        assertEquals(game.show(), "Empate\n" +
                "| B R B R B |\n" +
                "| R B R B R |\n" +
                "| B R B R B |\n" +
                "| R B R B R |\n");
}

    private void playSequenceOfMoves( Linea game, int ... moves ) {
        for (int i = 0; i < moves.length; i+= 2 )  {
            game.playRedAt( moves[i] );
            if ( i + 1 < moves.length ) {
                game.playBlueAt(moves[i + 1]);
            }
        }
    }

    private void movesToVerticalWinWithRed(Linea game) {
        playSequenceOfMoves( game, 1, 2, 1, 2, 1, 2, 1 );
    }

    private void movesToRowWinWithRed(Linea game) {
        playSequenceOfMoves(game, 1, 1, 2, 2, 3, 3, 4 );
    }

    private void movesToDiagonalWinWithRed(Linea game) {
        playSequenceOfMoves( game, 1, 2, 2, 3, 4, 3, 3, 4, 5, 4, 4 );
    }

    private Linea defaultGameB() {
        return new Linea( 7, 6, 'B');
    }

    private Linea defaultGameC() {
        return new Linea(7, 6, 'C');
    }

    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( assertThrows( RuntimeException.class, executable).getMessage(), message );
    }

}