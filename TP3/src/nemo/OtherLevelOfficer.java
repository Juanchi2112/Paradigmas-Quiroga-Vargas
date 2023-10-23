package nemo;

public class OtherLevelOfficer extends DepthOfficer {

    public DepthOfficer officerBelow() {
        return DepthOfficer.OtherLevelOfficer();
    }

    public void releaseCapsule() {
        throw new RuntimeException( Nemo.DestructionMessage );
    }

}