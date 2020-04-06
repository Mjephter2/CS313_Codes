package lecture7;

public class LinkedPositionalList<E> implements PositionalList<E> {

	public static void main(String[] args) {
		LinkedPositionalList<Integer> list1 = new LinkedPositionalList<>();
		LinkedPositionalList<Integer> list2 = new LinkedPositionalList<>();
		for(int i = 0; i < 10; i++){
			if(i%2 == 0) {
				list1.addFirst(i);
			}else{
				list2.addFirst(i);
			}
		}
		System.out.println("list1: " + list1);
		System.out.println("list2: " + list2);
		System.out.println("*******************");
		Position<Integer> pos1_list1 = list1.first();
		Position<Integer> pos1_list2 = list2.first();
		//System.out.println(list2.after(pos1_list1).getData());
		System.out.println(list1.after(pos1_list2));
	}
	
	private static class Node<E> implements Position<E> {

		private E data;
		private Node<E> prev, next;
		
		public Node(E data, Node<E> prev, Node<E> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public E getData() throws IllegalStateException {
			if (this.next == null) throw new IllegalStateException("Invalid Position");
			return this.data;
		}
		
	}
	
	private Node<E> header, trailer;
	private int size;
	
	public LinkedPositionalList() {
		this.size = 0;
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, null, null);
		this.header.next = this.trailer;
		this.trailer.prev = this.header;
	}
	
	private Position<E> position(Node<E> n) {
		if (n.data == null) return null;
		return n;
	}
	
	private Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position");
		Node<E> node = (Node<E>) p;
		if (node.next == null) throw new IllegalArgumentException("Invalid position");

		/*
		 * Homework
		 * # 2
		 * This change is necessary because without it we can access a list from another one.
		 * Look at nested main function for example
		 */
		Node<E> n = header.next;
		while(n.next != trailer){
			if(node.data != n.data){
				n = n.next;
				continue;
			}else{
				break;
			}
		}
		if(n.next == trailer){
			throw new IllegalArgumentException("Invalid position");
		}else{
			return node;
		}
	}
	
	private Position<E> addBetween(E data, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<>(data, prev, next);
		prev.next = newNode;
		next.prev = newNode;
		this.size++;
		return this.position(newNode);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public Position<E> first() {
		return this.position(this.header.next);
	}

	@Override
	public Position<E> last() {
		return this.position(this.trailer.prev);
	}

	@Override
	public Position<E> addFirst(E data) {
		return this.addBetween(data, this.header, this.header.next);
	}

	@Override
	public Position<E> addLast(E data) {
		return this.addBetween(data, this.trailer.prev, this.trailer);
	}

	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.position(n.prev);
	}

	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.position(n.next);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E data) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.addBetween(data, n.prev, n);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E data) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.addBetween(data, n, n.next);
	}

	@Override
	public E set(Position<E> p, E data) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		E old = n.data;
		n.data = data;
		return old;
	}

	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		Node<E> prev = n.prev;
		Node<E> next = n.next;
		prev.next = next;
		next.prev = prev;
		E val = n.data;
		
		//set all node data to null so the original position p is no longer valid
		n.next = null;
		n.prev = null;
		n.data = null;
		
		this.size--;
		return val;
	}

	/*
	 * Homework
	 * #1
	 */
	public String toString(){
		if(size == 0) return "[]";
		else {
			StringBuilder s = new StringBuilder();
			s.append("[");
			Position<E> curr = header.next;
			for(int i = 0; i < size - 1; i++){
				s.append(curr.getData() + ",");
				curr = after(curr);
			}
			s.append(curr.getData() + "]");
			return s.toString();
		}
	}

}
