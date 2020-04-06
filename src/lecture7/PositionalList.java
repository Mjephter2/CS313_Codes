package lecture7;

public interface PositionalList<E> {
	
	int size();
	
	boolean isEmpty();
	
	Position<E> first();
	
	Position<E> last();
	
	Position<E> addFirst(E data);
	
	Position<E> addLast(E data);
	
	Position<E> before(Position<E> p) throws IllegalArgumentException;
	
	Position<E> after(Position<E> p) throws IllegalArgumentException;
	
	Position<E> addBefore(Position<E> p, E data) throws IllegalArgumentException;
	
	Position<E> addAfter(Position<E> p, E data) throws IllegalArgumentException;
	
	E set(Position<E> p, E data) throws IllegalArgumentException;
	
	E remove(Position<E> p) throws IllegalArgumentException;

}
