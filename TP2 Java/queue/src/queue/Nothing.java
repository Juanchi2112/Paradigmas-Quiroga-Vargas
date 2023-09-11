package queue;

public class Nothing extends Maybe {
	
	public Object contains() { throw new Error ( "Queue is empty" ); }
	
}
