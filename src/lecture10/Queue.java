package lecture10;

import java.util.Comparator;

public interface Queue<E> {

	void enqueue(E e);
	
	E dequeue();
	
	E first();
	
	int size();
	
	boolean isEmpty();

	//void sort(Comparator<E> comp);
	
}
