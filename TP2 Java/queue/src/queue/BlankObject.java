package queue;

public class BlankObject extends DataObject {
	
	public static String ErrorMessage = "Queue is empty";

	public Object content() { throw new RuntimeException ( ErrorMessage ); }
	
}