package queue;

import java.util.ArrayList;
import java.util.List;

public class NonEmptyList extends MyList {
	
	public List<Object> lista = new ArrayList<>();
	
    public boolean isEmpty() {
		return false;
		}

	public MyList add( Object element ) {
		lista.add(element);
		return this;
		}

	public Object take() {
        return lista.remove(0);
        }

	public Object head() {
		return lista.get(0);
		}
	
	public int size() {
		return lista.size();
		}
	
	public List<Object> getList() {
		return lista;
	}
	
}
