package lecture16;

import java.util.LinkedList;
import java.util.List;
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

	// O(1)
	private Node<E> validate(Position<E> p) {
		if(! (p instanceof Node)) throw new IllegalArgumentException("Position is not the correct type");
		Node<E> node = (Node<E>) p;
		if(node.getParent() == node) throw new IllegalArgumentException("Position is not in the tree.");
		return node;
	}

	// O(1)
	@Override
	public Position<E> root() {
		return this.root;
	}

	// O(n)
	@Override
	public Position<E> parent(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getParent();
	}

	// O(n)
	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		Node<E> node = this.validate(p);
		List<Node<E>> c = node.children;
		List<Position<E>> cp = new LinkedList<>();
		for(Node<E> currNode: c){
			cp.add(currNode);
		}
		return cp;
	}

	@Override
	public int numChildren(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getChildren().size();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<E> iterator() {
		// do not complete
		return null;
	}

	@Override
	public Iterator<Position<E>> positions() {
		// do not complete
		return null;
	}

	public Position<E> addRoot(E e) throws IllegalStateException {
		if(this.isEmpty()) throw new IllegalStateException("Tree is not empty");
		Node<E> newRoot = new Node<>(e,null, null);
		this.size++;
		return newRoot;
	}

	public Position<E> addChild(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		Node<E> newChild = new Node<E>(e, node, null);
		node.getChildren().add(newChild);
		this.size++;
		return newChild;
	}
	
	public E set(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		E oldData = node.getElement();
		node.setElement(e);
		return oldData;
	}
	
	public E remove(Position<E> p) {
		Node<E> node = this.validate(p);
		E oldData = node.getElement();
		if(node.getChildren() != null) throw new IllegalStateException("Not allowed to remove Position with children");
		node.getParent().getChildren().remove(oldData);
		node.setParent(node);
		return oldData;
	}
	
	
}
