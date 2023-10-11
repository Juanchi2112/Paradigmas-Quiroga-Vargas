package nemo;

public abstract class DepthLevel {

    public static DepthLevel Surface() { return new SurfaceLevel(); }
    public static DepthLevel FirstLevel() { return new FirstLevel(); }
    public static DepthLevel OtherLevel() { return new OtherLevel(); }

    public abstract DepthLevel below();
    public abstract DepthLevel toBeRemoved();
    public void releaseCapsuleAtThisLevel() {}

}
