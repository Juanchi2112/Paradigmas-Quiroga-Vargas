package linea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineaTest {

    protected Linea defaultGame;

    @BeforeEach public void setUp() {
        defaultGame = new Linea( 7, 6, 'A' );
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
        defaultGame.playRedAt(1);
        assertEquals( 'R', defaultGame.pieceAt(6,1) );
    }

    @Test public void testColumnOutOfRange() {
        assertThrowsLike( () -> defaultGame.playRedAt(8), Linea.ColumnOutOfRangeMessage );
    }

    @Test public void testColumnOutOfRange2() {
        assertThrowsLike( () -> defaultGame.playRedAt(0), Linea.ColumnOutOfRangeMessage );
    }

    @Test public void testRedPlaysTwice() {
        defaultGame.playRedAt(1);
        assertThrowsLike( () -> defaultGame.playRedAt(1), GameStatus.NotRedTurnMessage);
    }

    @Test public void testBluePlaysFirst() {
        assertThrowsLike( () -> defaultGame.playBlueAt(1), GameStatus.NotBlueTurnMessage);
    }

    @Test public void testBluePlaysSecond() {
        defaultGame.playRedAt(1);
        defaultGame.playBlueAt( 1 );
        assertEquals( 'B', defaultGame.pieceAt(5, 1 ) );
    }

    @Test public void testBluePlaysTwice() {
        defaultGame.playRedAt(1);
        defaultGame.playBlueAt( 1 );
        assertThrowsLike( () -> defaultGame.playBlueAt(1), GameStatus.NotBlueTurnMessage);
    }

    @Test public void testTurnsAlternateCorrectly() {
        defaultGame.playRedAt(1);
        defaultGame.playBlueAt(1);
        defaultGame.playRedAt(1);
        defaultGame.playBlueAt(2 );
        defaultGame.playRedAt(2 );
        defaultGame.playBlueAt(2);

        assertEquals( defaultGame.pieceAt(4, 1), 'R' );
        assertEquals( defaultGame.pieceAt(4, 2), 'B' );
    }

    @Test public void testTurnsAlternateCorrectly2() {
        defaultGame.playRedAt(1);
        defaultGame.playBlueAt(1);
        defaultGame.playRedAt(1);
        defaultGame.playBlueAt(2 );

        defaultGame.playRedAt(2 );
        assertThrowsLike( () -> defaultGame.playRedAt(2), GameStatus.NotRedTurnMessage);

        defaultGame.playBlueAt(2);
        assertThrowsLike( () -> defaultGame.playBlueAt(2), GameStatus.NotBlueTurnMessage);
    }

    @Test public void testFullColumn() {
        playSequenceOfMoves( defaultGame,1, 1, 1, 1, 1, 1 );
        assertThrowsLike( () -> defaultGame.playRedAt(1), Linea.FullColumnMessage );
    }

    @Test public void testRedWinsWith4InARow() {
        movesToRowWinWithRed(defaultGame);

        assertTrue( defaultGame.redWins() );
        assertTrue( defaultGame.finished() );
    }

    @Test public void testBlueWinsWith4InARow() {
        playSequenceOfMoves( defaultGame, 5, 1, 1, 2, 2, 3, 3, 4) ;

        assertTrue( defaultGame.blueWins() );
        assertTrue( defaultGame.finished() );
    }

    @Test public void testRedWinsWith4InAColumn() {
        movesToVerticalWinWithRed(defaultGame);

        assertTrue( defaultGame.redWins() );
        assertTrue( defaultGame.finished() );
    }

    @Test public void testBlueWinsWith4InAColumn() {
        playSequenceOfMoves(defaultGame, 1, 2, 1, 2, 1, 2, 3, 2 );

        assertTrue(defaultGame.blueWins());
        assertTrue(defaultGame.finished());
    }

    @Test public void testPlayerDoesNotWinWith4InADiagonalInVariantA() {
        movesToDiagonalWinWithRed(defaultGame);
        assertEquals(defaultGame.pieceAt(3, 4), 'R');
        assertFalse(defaultGame.win());
        assertFalse(defaultGame.finished());

    }

    @Test public void testRedWinsWith4InADiagonalInVariantB() {
        Linea game = new Linea( 7, 6, 'B');
        movesToDiagonalWinWithRed(game);
        assertEquals(game.pieceAt(3, 4), 'R');
        assertTrue(game.redWins());
        assertTrue(game.finished());
    }

    @Test public void testBlueWinsWith4InADiagonalInVariantB() {
        Linea game = new Linea( 7, 6, 'B');
        playSequenceOfMoves( game, 6, 1, 2, 2, 3, 4, 3, 3, 4, 5, 4, 4);

        assertEquals(game.pieceAt(3, 4), 'B');
        assertTrue(game.blueWins());
        assertTrue(game.finished());
    }

    @Test public void testPlayerDoesNotWinWith4InAColumnOrRowInVariantB() {
        Linea game = new Linea(7, 6, 'B');
        playSequenceOfMoves( game, 1, 2, 1, 2, 1, 4, 1);

        assertFalse(game.redWins());

        game.playBlueAt(5);
        assertFalse(game.blueWins());
        assertFalse(game.finished());
    }

    @Test public void testPlayerWinsWith4InARowInVariantC() {
        Linea game = new Linea( 7, 6, 'C');
        movesToRowWinWithRed(game);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    private void movesToRowWinWithRed(Linea game) {
        playSequenceOfMoves(game, 1, 1, 2, 2, 3, 3, 4 );
    }

    @Test public void testPlayerWinsWith4InAColumnInVariantC() {
        Linea game = new Linea( 7, 6, 'C');
        movesToVerticalWinWithRed(game);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testPlayerWinsWith4InADiagonalInVariantC() {
        Linea game = new Linea( 7, 6, 'C');
        movesToDiagonalWinWithRed(game);

        assertTrue( game.redWins() );
        assertTrue( game.finished() );
    }

    @Test public void testGameIsFinishedAfterAWinAndItIsNotPossibleToContinuePlaying() {
        Linea game = new Linea( 7, 6, 'A');
        playSequenceOfMoves( game, 1, 1, 2, 2, 3, 3, 4 );


        assertTrue( game.win() );
        assertTrue( game.finished() );
        assertThrowsLike( () -> game.playRedAt(1), GameStatus.FinishedGameMessage );
    }

    @Test public void testGameIsFinishedAfterADrawAndItIsNotPosibleToContinuePlaying() {
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

    private void movesToDiagonalWinWithRed(Linea game) {
        playSequenceOfMoves( game, 1, 2, 2, 3, 4, 3, 3, 4, 5, 4, 4 );
    }

    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( assertThrows( RuntimeException.class, executable).getMessage(), message );
    }

}
