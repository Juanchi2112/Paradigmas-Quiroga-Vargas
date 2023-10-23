package nemo;

import java.util.ArrayList;
import java.util.List;

public class Nemo {

    public static String DestructionMessage = "¡¡Submarino destruído por exceso de chocolate!!";

    private Coordinate position;
    private List<DepthOfficer> depthOfficers = new ArrayList<>();
    private Direction direction;

    public Nemo( Coordinate initialPosition, Direction initialDirection ) {
        position = initialPosition;
        direction = initialDirection;
        depthOfficers.add( DepthOfficer.SurfaceOfficer() );
    }

    public void executeCommands( String commands ) {
        commands.chars().forEach( key -> Command.find( (char) key ).execute( this ) );
    }

    public void rotateLeft() {
        direction = direction.left();
    }

    public void rotateRight() {
        direction = direction.right();
    }

    public void descend() {
        depthOfficers.add( officerInCharge().officerBelow() );
    }

    public void ascend() {
        depthOfficers.remove( officerInCharge().toBeRemoved() );
    }

    public void moveForward() {
        position = position.add( direction.differential() );
    }

    public void releaseCapsule() {
        officerInCharge().releaseCapsule();
    }

    public DepthOfficer officerInCharge() { return depthOfficers.get( getDepth() ); }

    public Coordinate getPosition() { return position; }
    public int getDepth() { return depthOfficers.size() - 1; }
    public Direction getDirection() { return direction; }

}