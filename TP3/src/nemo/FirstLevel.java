package nemo;

public class FirstLevel extends DepthLevel {

    public DepthLevel below() {
        return DepthLevel.OtherLevel();
    }

    public DepthLevel toBeRemoved() {
        return this;
    }

}
