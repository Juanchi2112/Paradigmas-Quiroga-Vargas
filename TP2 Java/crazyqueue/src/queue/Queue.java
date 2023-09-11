package queue;

import java.util.List;

public class Queue {
	
	private MyList lista = new EmptyList();
	
	
	public boolean isEmpty() {
		return lista.isEmpty();
	}
	
	public Queue add( Object  cargo ) {
		lista = lista.add( cargo );
		return this;
	}
	
	public Object take() {
	    Object element = lista.take();
	    List<Object> resto = lista.getList();
	    lista = new EmptyList().extend(resto);
	    return element;
	}

	public Object head() {
		return lista.head();
	}
	
	public int size() {
		return lista.size();
	}

}
