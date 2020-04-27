package lecture6;

public class CircularlyLinkedList<E> {

	/*
	 * Nested node class identical to that of a SinglyLinkedList class
	 */
	private static class Node<E> {
		
		private E data;
		private Node<E> next;
		
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
		
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
		
	}
	
	private Node<E> tail;
	private int size = 0;
	
	//does not use a sentinel header node
	public CircularlyLinkedList() { }
	
	//O(1)
	public void rotate() {
		if(this.isEmpty()) return;
		this.tail = this.tail.next;
	}
	
	//O(1)
	public E first() {
		if (isEmpty()) return null;
		return tail.next.data;
	}
	
	//O(1)
	public E last() {
		if (this.isEmpty()) return null;	
		return this.tail.data;
	}
	
	//O(1)
	public int size() {
		return this.size;
	}
	
	//O(1)
	public boolean isEmpty() {
		return size == 0;
	}
	
	//O(1)
	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e);
		if (this.isEmpty()) {
			this.tail = newNode;
			this.tail.next = tail;
		} else {
			newNode.next = this.tail.next;
			this.tail.next = newNode;
		}
		this.size++;
	}
	
	//O(1)
	public void addLast(E e) {
		this.addFirst(e);
		this.rotate();
	}
	
	//O(1)
	public E removeFirst() {
		Node<E> oldNode = this.tail.next;
		if (oldNode == this.tail) this.tail = null;
		else {
			this.tail.next = oldNode.next;
			size--;
		}
		return oldNode.data;
	}
	
	//O(n)
	public String toString() {
		
		if (this.isEmpty()) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		Node<E> current = this.tail.next;
		
		while(current.next != this.tail.next) {
			sb.append(String.format("%s --> ", current.data));
			current = current.next;
		}
		sb.append(String.format("%s --> %s]", current.data, current.next.data));
	
		return sb.toString();		
	}
	
}
