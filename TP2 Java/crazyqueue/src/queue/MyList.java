package queue;

import java.util.List;

public abstract class MyList {
	
	public abstract boolean isEmpty();
	public abstract Object take();
	public abstract Object head();
	public abstract MyList add( Object element );
	public abstract int size();
	public abstract List<Object> getList();
	
}
