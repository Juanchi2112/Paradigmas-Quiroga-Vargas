package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
	
	private List<Maybe> lista = new ArrayList<>();
	
	public Queue() { lista.add(new Nothing()); }

	public boolean isEmpty() { return size() == 0; }
	
	public Queue add( Object  cargo ) {
		lista.add( size() , new Just( cargo ) );
		return this;
	}
	
	public Object take() {
		lista.get(0).contains();
		return lista.remove(0).contains();
	}
	
	public Object head() { return lista.get(0).contains(); }
	
	public int size() { return lista.size() - 1; }

}
