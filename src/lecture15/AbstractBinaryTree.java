package lecture15;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	/** returns the Position of p's sibling (or null if no sibling exists). */
	public Position<E> sibling(Position<E> p) {
		Position parent = this.parent(p);
		if (parent == null) return null; // p is the root
		if (p == this.left(parent)) { // p is a left child
			return (Position<E>) this.right(parent);
		} else {// p is a right child
			return this.left(parent);
		}
	}

	/** returns the number of children of Position p. */
	public int numChildren(Position<E> p) {
		int count = 0;
		if (this.left(p) != null) count++;
		if (this.right(p) != null) count++;
		return count;
	}

	/** returns an iterable collection of the Positions representing p's children. */
	public Iterable<Position<E>> children(Position<E> p) {
		List<Position<E>> children = new ArrayList<Position<E>>(2);
		if (this.left(p) != null) children.add(this.left(p));
		if (this.right(p) != null) children.add(this.right(p));
		return children;
	}


}
