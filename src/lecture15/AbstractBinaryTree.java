//package lecture15;
//
//import lecture7.Position;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
//
//	public Position<E> sibling(Position<E> p) {
//		Position<E> parent = this.parent(p);
//		if (parent == null) return null;
//		if (p == this.left(parent)) {
//			return this.right(parent);
//		} else {
//			return this.left(parent);
//		}
//	}
//
//
//	public int numChildren(Position<E> p) {
//		int count = 0;
//		if (this.left(p) != null) count++;
//		if (this.right(p) != null) count++;
//		return count;
//	}
//
//	public Iterable<Position<E>> children(Position<E> p) {
//		List<Position<E>> children = new ArrayList<Position<E>>(2);
//		if (this.left(p) != null) children.add(this.left(p));
//		if (this.right(p) != null) children.add(this.right(p));
//		return children;
//	}
//
//
//}
