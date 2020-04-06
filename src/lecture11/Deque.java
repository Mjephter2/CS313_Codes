package lecture11;

public interface Deque<E>  {

	void addFirst(E e);
	void addLast(E e);
	E removeFirst();
	E removeLast();

	//for convenience
	E first();
	E last();
	int size();
	boolean isEmpty();
	
}
