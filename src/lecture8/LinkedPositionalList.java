package lecture8;

import lecture7.Position;
import lecture7.PositionalList;
import java.util.Iterator;

public class LinkedPositionalList<E> implements PositionalList<E>, Iterable<E> {

	public static void main(String[] args) {
		LinkedPositionalList<Integer> list1 = new LinkedPositionalList<>();
		for(int i = 0; i < 10; i++){
			list1.addLast(i);
		}
		System.out.println("Initial list1\n" + list1);

		Iterator<Integer> iter1 = list1.iterator();
		System.out.println("list1 iterator created...");
		System.out.println("Adding integer 10 to list1...");

		list1.addFirst(10);
		System.out.println("list1 after adding 10\n" + list1);
		System.out.print("iter1 items after list1 change: ");
		while(iter1.hasNext()){
			System.out.print(iter1.next() + " ");
		}

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
	
	private class LinkedIterator implements Iterator<E> {

		private Position<E> cursor = LinkedPositionalList.this.first();
		private Position<E> lastReturned = null;
		
		@Override
		public boolean hasNext() {
			return this.cursor != null;
		}

		@Override
		public E next() {
			this.lastReturned = this.cursor;
			this.cursor = LinkedPositionalList.this.after(this.cursor);
			return this.lastReturned.getData();
		}
		
		@Override
		public void remove() {
			if (this.lastReturned == null) throw new IllegalStateException();
			LinkedPositionalList.this.remove(this.lastReturned);
			this.lastReturned = null;
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
	
	
	//O(1)
	private Position<E> position(Node<E> n) {
		if (n.data == null) return null;
		return n;
	}
	
	//O(1)
	private Node<E> validate(Position<E> p) {
		if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid position");
		Node<E> node = (Node<E>) p;
		if (node.next == null) throw new IllegalArgumentException("Invalid position");
		return node;
	}
	
	//O(1)
	private Position<E> addBetween(E data, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<>(data, prev, next);
		prev.next = newNode;
		next.prev = newNode;
		this.size++;
		return this.position(newNode);
	}

	//O(1)
	@Override
	public int size() {
		return this.size;
	}

	//O(1)
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	//O(1)
	@Override
	public Position<E> first() {
		return this.position(this.header.next);
	}

	//O(1)
	@Override
	public Position<E> last() {
		return this.position(this.trailer.prev);
	}

	//O(1)
	@Override
	public Position<E> addFirst(E data) {
		return this.addBetween(data, this.header, this.header.next);
	}

	//O(1)
	@Override
	public Position<E> addLast(E data) {
		return this.addBetween(data, this.trailer.prev, this.trailer);
	}

	//O(1)
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.position(n.prev);
	}

	//O(1)
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.position(n.next);
	}

	//O(1)
	@Override
	public Position<E> addBefore(Position<E> p, E data) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.addBetween(data, n.prev, n);
	}

	//O(1)
	@Override
	public Position<E> addAfter(Position<E> p, E data) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		return this.addBetween(data, n, n.next);
	}

	//O(1)
	@Override
	public E set(Position<E> p, E data) throws IllegalArgumentException {
		Node<E> n = this.validate(p);
		E old = n.data;
		n.data = data;
		return old;
	}

	//O(1)
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


	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator();
	}
}
