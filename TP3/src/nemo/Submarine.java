package nemo;

import java.util.ArrayList;
import java.util.List;


public class Submarine {

    public Coordinate position;
    public List<DepthLevel> depthLevels = new ArrayList<>();
    public Direction direction = Direction.East();
    public Channel channel = new Channel( this );
    public static String DestructionMessage = "¡¡Submarino destruído por exceso de chocolate!!";

    public Submarine( int x, int y ) {
        position = new Coordinate( x, y );
        depthLevels.add( DepthLevel.Surface() );
    }

    public void executeCommands( String commands ) {
        commands.chars().mapToObj(c -> (char) c).
                forEach( key -> channel.findCommand( key ).execute() );
    }

    public void rotateLeft() {
        direction = direction.left();
    }

    public void rotateRight() {
        direction = direction.right();
    }

    public void descend() {
        depthLevels.add( currentDepthLevel().below() );
    }

    public void ascend() {
        depthLevels.remove( currentDepthLevel().toBeRemoved() );
    }

    public void moveForward() {
        position = direction.addOneInThisDirection( position );
    }

    public void releaseCapsule() {
        currentDepthLevel().releaseCapsuleAtThisLevel();
    }

    public DepthLevel currentDepthLevel() { return depthLevels.get( getDepth() ); }

    public Coordinate getPosition() { return position; }
    public int getDepth() { return depthLevels.size() - 1; }
    public String getDirection() { return direction.toString(); }

}
