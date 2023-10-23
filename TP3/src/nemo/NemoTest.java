package nemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NemoTest {

    private Coordinate zeroPoint;
    private Direction east;
    private Direction south;
    private Direction west;
    private Direction north;
    private Nemo nemo;

    @BeforeEach public void setUp() {
        zeroPoint = new Coordinate(0, 0);
        east = Direction.East();
        south = Direction.South();
        west = Direction.West();
        north = Direction.North();
        nemo = new Nemo(zeroPoint, east);
    }

    @Test public void testNewNemo() {
        ensureNemoCurrentState( nemo, zeroPoint, 0, east );
    }

    @Test public void testNewNemoCanBeInstantiatedDifferently() {
        Nemo otherNemo = new Nemo( new Coordinate(1, 1), west );
        ensureNemoCurrentState( otherNemo, new Coordinate(1, 1), 0, west );
    }

    @Test public void testEmptyCommandString() {
        nemo.executeCommands("");
        ensureNemoCurrentState( nemo , zeroPoint, 0, east );
    }

    @Test public void testNemoDescendsOneLevelCorrectly() {
        nemo.executeCommands("d");
        assertEquals(nemo.getDepth(), 1);
    }

    @Test public void testNemoDescendsTwoLevelsCorrectly() {
        nemo.executeCommands("dd");
        assertEquals(nemo.getDepth(), 2);
    }

    @Test public void testNemoDescendsAsManyLevelsAsNeededCorrectly() {
        nemo.executeCommands("dddddddddddd");
        assertEquals(nemo.getDepth(), 12);
    }

    @Test public void testNemoRemainsTheSameWhenAscendInTheSurface() {
        nemo.executeCommands("u");
        ensureNemoCurrentState( nemo, zeroPoint, 0, east );
    }

    @Test public void testNemoAscendsCorrectly() {
        nemo.executeCommands("du");
        assertEquals(nemo.getDepth(), 0);
    }

    @Test public void testNemoTurnsRightCorrectly() {
        nemo.executeCommands("l");
        ensureNemoCurrentState(nemo, zeroPoint, 0, north );
    }

    @Test public void testNemoTurnsLeftCorrectly() {
        nemo.executeCommands( "r" );
        ensureNemoCurrentState( nemo, zeroPoint, 0, south );
    }

    @Test public void testNemoTurnsLeftAndRightCorrectly() {
        nemo.executeCommands( "lr" );
        ensureNemoCurrentState( nemo, zeroPoint, 0, east );
    }

    @Test public void testNemoFullRotation() {
        nemo.executeCommands( "r" );
        assertEquals( nemo.getDirection(), south );

        nemo.executeCommands( "r");
        assertEquals( nemo.getDirection(), west );

        nemo.executeCommands( "r");
        assertEquals( nemo.getDirection(), north );

        nemo.executeCommands( "r");
        assertEquals( nemo.getDirection(), east );
    }

    @Test public void testNemoMovesForwardCorrectly() {
        nemo.executeCommands( "f" );
        assertEquals( nemo.getPosition(), new Coordinate(1, 0 ) );
    }

    @Test public void testNemoMovesForwardTwiceCorrectly() {
        nemo.executeCommands( "ff" );
        assertEquals( nemo.getPosition(), new Coordinate(2, 0 ) );
    }

    @Test public void testNemoMovesInAnyDirectionCorrectly() {
        nemo.executeCommands( "lflf" );
        assertEquals( nemo.getPosition(), new Coordinate(-1, 1 ) );
    }

    @Test public void testNemoReleaseTheCapsuleOnTheSurfaceWithNoVisibleEffect() {
        nemo.executeCommands( "m" );
        ensureNemoCurrentState( nemo, zeroPoint, 0, east );
    }

    @Test public void testNemoReleaseTheCapsuleInTheFirstDepthLevelWithNoVisibleEffect() {
        nemo.executeCommands( "dm" );
        ensureNemoCurrentState( nemo, zeroPoint, 1, east );
    }

    @Test public void testNemoExplodeReleasingTheCapsuleInAForbiddenLevelDepth() {
        nemo.executeCommands( "dd" );
        assertThrowsLike( () -> nemo.executeCommands( "m" ),
                          Nemo.DestructionMessage );
    }

    @Test public void testNemoExplodeReleasingTheCapsuleInADeeperForbiddenLevelDepth() {
        nemo.executeCommands( "dddd" );
        assertThrowsLike( () -> nemo.executeCommands( "m" ),
                          Nemo.DestructionMessage );
    }

    @Test public void testNemoCompleteBehaviourOnTheSurface(){
        nemo.executeCommands( "lflfrm" );
        ensureNemoCurrentState( nemo, new Coordinate (-1,1), 0, north );
    }

    @Test public void testNemoCompleteBehaviourInTheFirstDepthLevel(){
        nemo.executeCommands( "d" );
        nemo.executeCommands( "ffllmlf" );
        ensureNemoCurrentState( nemo,new Coordinate (2,-1), 1, south );
    }

    @Test public void testNemoCompleteBehaviourTwoLevelsDownTheSurface(){
        nemo.executeCommands("dd");
        nemo.executeCommands( "flflflfl" );
        ensureNemoCurrentState( nemo, zeroPoint, 2, east );
        assertThrowsLike( () -> nemo.executeCommands( "m" ),
                Nemo.DestructionMessage );
    }

    @Test public void testNemoBehavesCorrectlyWithMultipleCommands(){
        nemo.executeCommands("dflrum");
        ensureNemoCurrentState( nemo, new Coordinate(1,0), 0, east );
    }

    private void ensureNemoCurrentState(Nemo nemo, Coordinate position, int depth, Direction direction ) {
        assertEquals( nemo.getPosition(), position );
        assertEquals( nemo.getDepth(), depth );
        assertEquals( nemo.getDirection(), direction );
    }

    private void assertThrowsLike( Executable executable, String message ) {
        assertEquals( assertThrows( RuntimeException.class, executable ).getMessage(), message );
    }

}