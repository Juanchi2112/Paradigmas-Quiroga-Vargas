package queue;

public class Just extends Maybe {
	
	private Object element;
	
	public Just( Object cargo ) { element = cargo; }
	
	public Object contains() { return element; }
	
}
