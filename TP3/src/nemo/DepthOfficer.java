package nemo;

public abstract class DepthOfficer {

    public static DepthOfficer SurfaceOfficer() {
        return new SurfaceOfficer();
    }

    public static DepthOfficer FirstLevelOfficer() {
        return new FirstLevelOfficer();
    }

    public static DepthOfficer OtherLevelOfficer() {
        return new OtherLevelOfficer();
    }

    public DepthOfficer toBeRemoved() {
        return this;
    }

    public void releaseCapsule() {}

    public abstract DepthOfficer officerBelow();

}