package nemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTest {

    private Coordinate zeroPoint;
    @BeforeEach public void setUp() {
        zeroPoint = new Coordinate( 0, 0 );
    }

    @Test public void testNewNemo() {
        ensureSubmarineCurrentState(NewNemo(), zeroPoint, 0, "East" );
    }

    @Test public void testEmptyCommand() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "" );
        ensureSubmarineCurrentState(NewNemo(), zeroPoint, 0, "East" );
    }

    @Test public void testNemoDescendsOneLevelCorrectly() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "d" );
        assertEquals( nemo.getDepth(), 1 );
    }

    @Test public void testNemoAscendsOneLevelCorrectly() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "u" );
        assertEquals( nemo.getDepth(), 0 );
    }

    @Test public void testNemoNoAscendsMoreThanSurface() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "du" );
        assertEquals( nemo.getDepth(), 0 );
    }

    @Test public void testTurnsRightCorrectly() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "l" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "North" );}

        @Test public void testTurnsLeftCorrectly() {
            Submarine nemo = NewNemo();
        nemo.executeCommands( "r" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "South" );
    }
    @Test public void testTurnsLeftandRightCorrectly() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "lr" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "East" );
    }
    @Test public void testTurnsRightandLeftCorrectly() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "rl" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "East" );
    }

    @Test public void testMovesForwardCorrectly() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "f" );
        assertEquals( nemo.getPosition(), new Coordinate(1, 0 ) );

        nemo.executeCommands( "lf" );
        assertEquals( nemo.getPosition(), new Coordinate( 1,1 ) );
    }

    @Test public void testNemoRealeaseTheCapsuleOnTheSurface() {
        Submarine nemo = NewNemo();
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "East" );

        nemo.executeCommands( "m" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "East" );
    }

    @Test public void testNemoRealeaseTheCapsuleOneLevelDownTheSurface() {
        Submarine nemo = NewNemo();
        ensureSubmarineCurrentState( nemo, zeroPoint, 0, "East" );

        nemo.executeCommands( "dm" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 1, "East" );
    }

    @Test public void testNemoExploteRealeasingTheCapsuleTwoLevelDownTheSurface() {
        Submarine nemo = NewNemo();
        nemo.executeCommands( "dd" );
        assertThrowsLike( () -> nemo.executeCommands( "m" ),
                          Submarine.DestructionMessage );
    }
    @Test public void testNemoFullFillsAllItsFunctionsOnTheSurface(){
        Submarine nemo = NewNemo();
        nemo.executeCommands( "lflfrm" );
        ensureSubmarineCurrentState( nemo, new Coordinate (-1,1), 0, "North" );
    }
    @Test public void testNemoFullFillsAllItsFunctionsOneLevelDownTheSurface(){
        Submarine nemo = NewNemo();
        nemo.executeCommands( "d" );
        nemo.executeCommands( "ffllmlf" );
        ensureSubmarineCurrentState( nemo,new Coordinate (2,-1), 1, "South" );
    }
    @Test public void testNemoFullFillsAllItsFunctionsTwoLevelsDownTheSurface(){
        Submarine nemo = NewNemo();
        nemo.executeCommands("dd");
        nemo.executeCommands( "flflflfl" );
        ensureSubmarineCurrentState( nemo, zeroPoint, 2, "East" );
        assertThrowsLike( () -> nemo.executeCommands( "m" ),
                Submarine.DestructionMessage );
    }
    @Test public void test(){

    }
    private void ensureSubmarineCurrentState( Submarine submarine, Coordinate position, int depth, String direction ) {
        assertEquals( submarine.getPosition(), position );
        assertEquals( submarine.getDepth(), depth );
        assertEquals( submarine.getDirection(), direction );
    }

    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( assertThrows( RuntimeException.class, executable ).getMessage(), message );
    }
    private static Submarine NewNemo() {
        return new Submarine(0, 0);
    }

}
