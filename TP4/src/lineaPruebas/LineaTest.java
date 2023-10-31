package lineaPruebas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineaTest {

    @Test public void testEmpty4x4Board() {
        Linea game = new Linea( 4, 4, 'A' );
        assertEquals( game.show(), "|         |\n|         |\n|         |\n|         |\n");
    }
    @Test public void test4x4BoardWithOneChipOfEachColor() {
        Linea game = new Linea( 4, 4, 'A' );
        game.playRedAt(1);
        game.playBlueAt(2);
        assertEquals( game.show(), "|         |\n|         |\n|         |\n| R B     |\n");
    }
    @Test public void testRedTeamWinsHorizontalCorrectly() {
        Linea game = new Linea( 4, 4, 'A' );
        game.playRedAt(1);
        game.playRedAt(2);
        game.playRedAt(3);
        game.playRedAt(4);
        assertEquals( game.show(), "|         |\n|         |\n|         |\n| R R R R |\n");
        assertTrue(game.finished());
    }
    @Test public void testBlueTeamWinsHorizontalCorrectly() {
        Linea game = new Linea( 4, 4, 'A' );
        game.playBlueAt(1);
        game.playBlueAt(2);
        game.playBlueAt(3);
        game.playBlueAt(4);
        assertEquals( game.show(), "|         |\n|         |\n|         |\n| B B B B |\n");
        assertTrue(game.finished());
    }

    @Test public void testRedTeamWinsVerticalCorrectly() {
        Linea game = new Linea( 4, 4, 'A' );
        game.playRedAt(1);
        game.playRedAt(1);
        game.playRedAt(1);
        game.playRedAt(1);
        assertEquals( game.show(), "| R       |\n| R       |\n| R       |\n| R       |\n");
        assertTrue(game.finished());
    }

    @Test public void testBlueTeamWinsVerticalCorrectly() {
        Linea game = new Linea( 4, 4, 'A' );
        game.playBlueAt(1);
        game.playBlueAt(1);
        game.playBlueAt(1);
        game.playBlueAt(1);
        assertEquals( game.show(), "| B       |\n| B       |\n| B       |\n| B       |\n");
        assertTrue(game.finished());
    }

    @Test public void testNobodyWins() {
        Linea game = new Linea( 4, 4, 'A' );
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playRedAt(4);
        game.playBlueAt(4);
        game.playBlueAt(4);
        game.playRedAt(4);
        assertEquals( game.show(), "| B B R R |\n| R R B B |\n| R R B B |\n| B B R R |\n");
        assertTrue(game.finished());
        assertTrue(game.draw());
    }
}
