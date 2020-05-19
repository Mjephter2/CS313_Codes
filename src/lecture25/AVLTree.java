package lecture25;

import lecture15.Position;

public class AVLTree<E> extends LinkedBinaryTree<E> {

	private static class AVLNode<E> extends Node<E> {
		
		private int height = 0;
		
		public AVLNode(E e, Node<E> p, Node<E> l, Node<E> r) {
			super(e, p, l, r);
		}
		
		public int getHeight() {
			return this.height;
		}
		
		public void setHeight(int h) {
			this.height = h;
		}
	}
	
	@Override
	protected Node<E> createNode(E e, Node<E> p, Node<E> l, Node<E> r) {
		return new AVLNode<>(e, p, l, r);
	}
	
	@Override
	public int height(Position<E> p) {
		return ((AVLNode) p).getHeight();
	}
	
	private int computeHeight(Position<E> p) {
		if (this.isExternal(p)) return 0;
		int h = 1 + Math.max(this.height(this.left(p)), this.height(this.right(p)));
		((AVLNode) p).setHeight(h);
		return h;
	}
	
	private boolean isBalanced(Position<E> p) {
		if (this.isExternal(p)) return true;
		return Math.abs(this.height(this.left(p)) - this.height(this.right(p))) <= 1;
	}
	
	private Position<E> tallerChild(Position<E> p) {
		Position<E> left = this.left(p);
		Position<E> right = this.right(p);
		if (this.height(left) > this.height(right)) return left;
		if (this.height(right) > this.height(left)) return right;
		if (p == this.root()) return left;
		if (p == this.left(this.parent(p))) return left;
		else return right;
	}
	
	private void relink(Node<E> parent, Node<E> child, boolean makeLeftChild) {
		child.setParent(parent);
		if (makeLeftChild)
			parent.setLeft(child);
		else 
			parent.setRight(child);
	}
	
	private void rotate(Position<E> p) {
		Node<E> x = this.validate(p);
		Node<E> y = x.getParent();
		Node<E> z = y.getParent();
		if (z == null) {
			this.root = x;
			x.setParent(null);
		} else {
			this.relink(z, x, y == z.getLeft());
		}
		if (x == y.getLeft()) {
			this.relink(y, x.getRight(), true);
			this.relink(x, y, false);
		} else {
			this.relink(y, x.getLeft(), false);
			this.relink(x, y, true);
		}
	}
	
	private Position<E> restructure(Position<E> x) {
		Position<E> y = this.parent(x);
		Position<E> z = this.parent(y);
		if ((x == this.right(y)) == (y == this.right(z))) {
			this.rotate(y);
			return y;
		} else {
			this.rotate(x);
			this.rotate(x);
			return x;
		}	
	}
	
	public void rebalance(Position<E> p) {
		int oldHeight, newHeight;
		do {
			oldHeight = this.height(p);
			if (!this.isBalanced(p)) {
				Position<E> x = this.tallerChild(this.tallerChild(p));
				p = this.restructure(x);
				this.computeHeight(this.left(p));
				this.computeHeight(this.right(p));
			}
			newHeight = this.computeHeight(p);
			p = this.parent(p);
		} while(oldHeight != newHeight && p != null);
	}
	
}
