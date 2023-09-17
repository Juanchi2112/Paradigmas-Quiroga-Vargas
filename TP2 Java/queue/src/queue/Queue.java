package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	
	private List<DataObject> lista = new ArrayList<>();
	
	public Queue() { 
		lista.add( new BlankObject() );
	}

	public boolean isEmpty() { 
		return size() == 0; 
	}
	
	public Queue add( Object  cargo ) {
		lista.add( size(), new FilledObject( cargo ) );
		return this;
	}
	
	public Object take() { 
		Object cargo = head();
		lista.remove(0);
		return cargo;
	}
	
	public Object head() {
		return lista.get(0).content(); 
	}
	
	public int size() { 
		return lista.size() - 1; 
	}

}