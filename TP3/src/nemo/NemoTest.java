package nemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTest {

    @Test public void test00() {
        Submarine nemo = new Submarine( 0, 0 );
        assertEquals( nemo.getPosition(), new Coordinate( 0, 0 ) );
        assertEquals( nemo.getDepth(), 0 );
        assertEquals( nemo.getDirection(), "East" );
    }

    @Test public void test01() {
        Submarine nemo = new Submarine( 0, 0 );
        nemo.executeCommands( "" );
        assertEquals( nemo.getPosition(), new Coordinate( 0, 0 ) );
        assertEquals( nemo.getDepth(), 0 );
        assertEquals( nemo.getDirection(), "East" );

    }

    @Test public void test02() {
        Submarine nemo = new Submarine( 0, 0 );
        nemo.executeCommands( "d" );
        assertEquals( nemo.getDepth(), 1 );
    }

    @Test public void test03() {
        Submarine nemo = new Submarine( 0, 0 );
        nemo.executeCommands( "u" );
        assertEquals( nemo.getDepth(), 0 );

        nemo.executeCommands( "du" );
        assertEquals( nemo.getDepth(), 0 );
    }

    @Test public void test04() {
        Submarine nemo = new Submarine( 0, 0 );
        nemo.executeCommands( "l" );
        assertEquals( nemo.getDirection(), "North" );

        nemo.executeCommands( "rr" );
        assertEquals( nemo.getDirection(), "South" );
    }

    @Test public void test05() {
        Submarine nemo = new Submarine( 0, 0 );
        nemo.executeCommands( "f" );
        assertEquals( nemo.getPosition(), new Coordinate(1, 0 ) );

        nemo.executeCommands( "lf" );
        assertEquals( nemo.getPosition(), new Coordinate( 1,1 ) );
    }

    @Test public void test06() {
        Submarine nemo = new Submarine( 0, 0 );
        nemo.executeCommands( "dd" );
        assertThrowsLike( () -> nemo.executeCommands( "m" ),
                          Submarine.DestructionMessage );
    }

    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( assertThrows( RuntimeException.class, executable ).getMessage(), message );
    }

}
