package lecture16;

import java.util.Iterator;

import lecture15.AbstractBinaryTree;
import lecture15.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	
	private static class Node<E> implements Position<E> {

		private E element;
		private Node<E> parent, left, right;
		
		public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
			this.element = e;
			this.parent = p;
			this.left = l;
			this.right = r;
		}

		public E getElement() {
			return element;
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

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}		
		
	}
	
	private Node<E> root;
	private int size;

	public LinkedBinaryTree() {
		this.root = null;
		this.size = 0;
	}
	
	//O(1)
	private Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) throw new IllegalArgumentException("Position is not the correct type");
		Node<E> node = (Node<E>) p;
		if (node.getParent() == node) throw new IllegalArgumentException("Position is no longer in the tree");
		return node;
	}
	
	//O(1)
	@Override
	public Position<E> root() {
		return this.root;
	}
	
	
	//O(1)
	@Override
	public Position<E> left(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getLeft();
	}

	//O(1)
	@Override
	public Position<E> right(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getRight();
	}

	//O(1)
	@Override
	public Position<E> parent(Position<E> p) {
		Node<E> node = this.validate(p);
		return node.getParent();
	}

	//O(1)
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public Iterator<Position<E>> positions() {
		return null;
	}

	//methods to construct/modify the tree
	//O(1)
	public Position<E> addRoot(E e) {
		if (!this.isEmpty()) throw new IllegalStateException("Tree is not empty");
		this.root = new Node<>(e, null, null, null);
		this.size = 1;
		return this.root;
	}
	
	//O(1)
	public Position<E> addLeft(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		if (node.getLeft() != null) throw new IllegalStateException("Left child already exists");
		Node<E> child = new Node<>(e, node, null, null);
		node.setLeft(child);
		this.size++;
		return child;
	}
	
	//O(1)
	public Position<E> addRight(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		if (node.getRight() != null) throw new IllegalStateException("Right child already exists");
		Node<E> child = new Node<>(e, node, null, null);
		node.setRight(child);
		this.size++;
		return child;
	}
	
	
	//O(1)
	public E set(Position<E> p, E e) {
		Node<E> node = this.validate(p);
		E old = node.getElement();
		node.setElement(e);
		return old;
	}
	
	//O(1)
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) {
		//make sure we can safely attach
		Node<E> node = this.validate(p);
		if (this.isInternal(p)) throw new IllegalStateException("Position must be a leaf");
		
		//connect t1 as left child
		if (t1 != null) {
			this.size += t1.size();
			
			t1.root.setParent(node);
			node.setLeft(t1.root);
			
			t1.root = null;
			t1.size = 0;
		}
		
		//connect t2 as right child
		if (t2 != null) {
			this.size += t2.size();
			
			t2.root.setParent(node);
			node.setRight(t2.root);
			
			t2.root = null;
			t2.size = 0;	
		}
		
	}
	
	//O(1)
	public E remove(Position<E> p) {
		Node<E> node = this.validate(p);
		
		if (this.numChildren(p) == 2) throw new IllegalStateException("Position has two children");
		
		E old = node.getElement();
		
		Node<E> child;
		if (node.getLeft() != null) {
			child = node.getLeft();
		} else {
			child = node.getRight();
		}
		
		if (child != null) {
			child.setParent(node.getParent());
		}
		
		if (node == this.root) {
			this.root = child;
		} else {
			Node<E> parent = node.getParent();
			if (node == parent.getLeft())
				parent.setLeft(child);
			else 
				parent.setRight(child);
		}
			
		node.setParent(node);
		node.setElement(null);
		node.setLeft(null);
		node.setRight(null);
		
		this.size--;
		
		return old;
	}
	
}