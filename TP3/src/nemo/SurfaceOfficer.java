package nemo;

public class SurfaceOfficer extends DepthOfficer {

    public DepthOfficer officerBelow() {
        return DepthOfficer.FirstLevelOfficer();
    }

    public DepthOfficer toBeRemoved() {
        return null;
    }

}