package nemo;

public class SurfaceLevel extends DepthLevel {

    public DepthLevel below() {
        return DepthLevel.FirstLevel();
    }
    public DepthLevel toBeRemoved() {
        return null;
    }

}
