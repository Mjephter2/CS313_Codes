package lecture15;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {

	Position<E> root();
	Position<E> parent(Position<E> p);
	Iterable<Position<E>> children(Position<E> p);
	int numChildren(Position<E> p);
	
	boolean isInternal(Position<E> p);
	boolean isExternal(Position<E> p);
	boolean isRoot(Position<E> p);
	
	int size();
	boolean isEmpty();
	Iterator<E> iterator();
	Iterator<Position<E>> positions();

	public static void main(String[] args) {

		//example usage of the children(...) method
		Tree<Integer> t = new Tree<>();
		Iterable<Position<Integer>> iterable = t.children(t.root());
		
		Iterator<Position<Integer>> iter = iterable.iterator();
		while(iter.hasNext()) {
			Position<Integer> current = iter.next();
		}
		
	}
}
