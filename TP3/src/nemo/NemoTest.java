package nemo;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class NemoTest {

    @Test public void test00() {
        Nemo nemo = new Nemo( 0, 0, 0 );
        assertEquals( nemo.getPosition(), new Point( 0, 0 ) );
        assertEquals( nemo.getDepth(), 0 );
        assertEquals( nemo.getDirection(), "East" );
    }

    @Test public void test01() throws Exception {
        Nemo nemo = new Nemo( 0, 0, 0 );
        nemo.executeCommands( "" );
        assertEquals( nemo.getPosition(), new Point( 0, 0 ) );
        assertEquals( nemo.getDepth(), 0 );
        assertEquals( nemo.getDirection(), "East" );

    }

    @Test public void test02() throws Exception {
        Nemo nemo = new Nemo( 0, 0, 0 );
        nemo.executeCommands( "d" );
        assertEquals( nemo.getDepth(), 1 );
    }

    @Test public void test03() throws Exception {
        Nemo nemo = new Nemo( 0, 0, 0 );
        nemo.executeCommands( "u" );
        assertEquals( nemo.getDepth(), 0 );

        nemo.executeCommands( "du" );
        assertEquals( nemo.getDepth(), 0 );
    }

    @Test public void test04() throws Exception {
        Nemo nemo = new Nemo( 0, 0, 0 );
        nemo.executeCommands( "l" );
        assertEquals( nemo.getDirection(), "North" );

        nemo.executeCommands( "rr" );
        assertEquals( nemo.getDirection(), "South" );
    }

    @Test public void test05() throws Exception {
        Nemo nemo = new Nemo( 0, 0, 0 );
        nemo.executeCommands( "f" );
        assertEquals( nemo.getPosition(), new Point(1, 0 ) );

        nemo.executeCommands( "lf" );
        assertEquals( nemo.getPosition(), new Point( 1,1 ) );
    }

}
