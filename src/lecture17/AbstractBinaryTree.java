package lecture17;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import lecture10.Queue;
import lecture10.LinkedQueue;
import lecture15.AbstractTree;
import lecture15.BinaryTree;
import lecture15.Position;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	private class ElementIterator implements Iterator<E> {

		private Iterator<Position<E>> positionIterator = AbstractBinaryTree.this.positions().iterator();
		
		@Override
		public boolean hasNext() {
			return this.positionIterator.hasNext();
		}

		@Override
		public E next() {
			return this.positionIterator.next().getElement();
		}
		
		@Override
		public void remove() {
			this.positionIterator.remove();
		}
		
	}
	
	public Position<E> sibling(Position<E> p) {
		Position<E> parent = this.parent(p);
		if (parent == null) return null;
		if (p == this.left(parent)) return this.right(parent);
		else return this.left(parent);
	}
	
	public int numChildren(Position<E> p) {
		int count = 0;
		if (this.left(p) != null) count++;
		if (this.right(p) != null) count++;
		return count;
	}
	
	public Iterable<Position<E>> children(Position<E> p) {
		List<Position<E>> children = new ArrayList<>(2);
		if (this.left(p) != null) children.add(this.left(p));
		if (this.right(p) != null) children.add(this.right(p));
		return children;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		return this.preorder();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			sb.append(iter.next() + ", ");
		}
		return sb.toString();		
	}
	
	public Iterable<Position<E>> preorder() {
		List<Position<E>> list = new ArrayList<>();
		preorderSubtree(this.root(), list);
		return list;
	}
	
	private void preorderSubtree(Position<E> p, List<Position<E>> list) {
		list.add(p);
		for (Position<E> c : this.children(p)) {
			preorderSubtree(c, list);
		}		
	}
	
	public Iterable<Position<E>> breadthfirst() {
		List<Position<E>> list = new ArrayList<>();
		if (!this.isEmpty()) {
			Queue<Position<E>> q = new LinkedQueue<>();
			q.enqueue(this.root());
			while (!q.isEmpty()) {
				Position<E> p = q.dequeue();
				list.add(p);
				for (Position<E> c : this.children(p))
					q.enqueue(c);
			}
		}
		return list;
	}
	
}
