package lecture16;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import lecture15.AbstractTree;
import lecture15.Position;

public class LinkedTree<E> extends AbstractTree<E> {
	
	private static class Node<E> implements Position<E> {

		private E element;
		private Node<E> parent;
		private List<Node<E>> children;
		
		public Node(E e, Node<E> p, List<Node<E>> c) {
			this.element = e;
			this.parent = p;
			this.children = c;
		}
		
		@Override
		public E getElement() {
			return this.element;
		}
		
		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public List<Node<E>> getChildren() {
			return children;
		}

		public void setChildren(List<Node<E>> children) {
			this.children = children;
		}
		
		public void addChild(Node<E> c) {
			this.children.add(c);
		}
		
	}
	
	private Node<E> root;
	private int size;
	
	public LinkedTree() {
		this.root = null;
		this.size = 0;
	}
	
	private Node<E> validate(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> parent(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numChildren(Position<E> p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// do not complete
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		// do not complete
		return null;
	}

	public Position<E> addRoot(E e) throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Position<E> addChild(Position<E> p, E e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public E set(Position<E> p, E e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public E remove(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
