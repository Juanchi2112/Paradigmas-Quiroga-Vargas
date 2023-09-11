package queue;

import java.util.List;

public class EmptyList extends MyList{

	public boolean isEmpty() { 
		return true; 
		}
	
	public Object take() { 
		throw new Error( "Queue is empty" ); 
		}
	
	public Object head() {
		throw new Error( "Queue is empty" );
		}
	
	public MyList add( Object element ) {
		return new NonEmptyList().add( element );
	}
	
	public int size() {
		return 0;
	}
	
	public MyList extend(List<Object> lista) {
		MyList nueva = new EmptyList();
		for (Object element: lista) {
			nueva = nueva.add(element);
		}
		return nueva;
	}
	
	public List<Object> getList() {
		return null;
	}
	
	 
	
}
