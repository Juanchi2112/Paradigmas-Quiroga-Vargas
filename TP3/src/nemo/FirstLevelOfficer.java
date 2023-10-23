package nemo;

public class FirstLevelOfficer extends DepthOfficer {

    public DepthOfficer officerBelow() {
        return DepthOfficer.OtherLevelOfficer();
    }

}