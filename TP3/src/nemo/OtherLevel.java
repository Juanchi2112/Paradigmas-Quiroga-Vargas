package nemo;

public class OtherLevel extends DepthLevel {

    public DepthLevel below() {
        return DepthLevel.OtherLevel();
    }

    public DepthLevel toBeRemoved() {
        return this;
    }

    public void releaseCapsuleAtThisLevel() {
        throw new RuntimeException( Submarine.DestructionMessage );
    }

}
