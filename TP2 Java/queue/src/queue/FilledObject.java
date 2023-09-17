package queue;

public class FilledObject extends DataObject {
	
	private Object element;
	
	public FilledObject( Object cargo ) { element = cargo; }
	
	public Object content() { return element; }
	
}
