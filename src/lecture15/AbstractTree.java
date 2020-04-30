package lecture15;


public abstract class AbstractTree<E> implements Tree<E> {

	public boolean isRoot(Position<E> p) {
		return p == this.root();
	}

	public boolean isInternal(Position<E> p) {
		return this.numChildren(p) > 0;
	}

	public boolean isExternal(Position<E> p) {
		return this.numChildren(p) == 0;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public int depth(Position<E> p) {
		//number of ancestors
		if (this.isRoot(p)) return 0;
		else return 1 + this.depth(this.parent(p));
	}

	public int height(Position<E> p) {
		int h = 0;
		for (Position<E> c: this.children(p)) {
			h = Math.max(h, 1 + this.height(c));
		}
		return h;
	}

}
