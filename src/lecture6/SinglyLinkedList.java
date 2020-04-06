package lecture6;

import lecture4.AbstractList;

public class SinglyLinkedList<E> extends AbstractList<E> {

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
	
	private Node<E> head, tail;
	private int size;
	
	//does not use a sentinel header node
	public SinglyLinkedList() {
		this.size = 0; 
	}
	
	//O(1)
	public E first() {
		if (this.isEmpty()) return null;
		return this.head.data;
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
		return this.head == null;
	}
	
	//O(1)
	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e);
		if (this.isEmpty()) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			newNode.next = this.head;
			this.head = newNode;
		}
		this.size++;
	}
	
	//O(1)
	public void addLast(E e) {
		Node<E> newNode = new Node<>(e);
		if (this.isEmpty()) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			this.tail.next = newNode;
			this.tail = newNode;
		}
		this.size++;
	}
	
	//O(1)
	public E removeFirst() {
		if (this.isEmpty()) return null;
		E val = this.head.data;
		this.head = this.head.next;
		this.size--;
		return val;
	}
	
	//O(n)
	public String toString() {
		
		if (this.isEmpty()) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		Node<E> current = this.head;
		
		while(current.next != null) {
			sb.append(String.format("%s --> ", current.data));
			current = current.next;
		}
		sb.append(String.format("%s --> null]", current.data));
	
		return sb.toString();		
	}

	@Override
	public void set(int i, E e) {
		if (i < 0 || i >= this.size) 
			throw new IndexOutOfBoundsException();
		Node<E> current = this.head;
		for (int j = 0; j < i; j++) {
			current = current.next;
		}
		current.data = e;
	}
	
	@Override
	public E get(int i) {
		if (i < 0 || i >= this.size) 
			throw new IndexOutOfBoundsException();
		Node<E> current = this.head;
		for (int j = 0; j < i; j++) {
			current = current.next;
		}
		return current.data;
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i > this.size) 
			throw new IndexOutOfBoundsException();
		if (this.isEmpty()) {
			this.addFirst(e);
			return;
		}
		Node<E> current = this.head;
		for (int j = 0; j < i-1; j++) {
			current = current.next;
		}
		Node<E> newNode = new Node<>(e);
		newNode.next = current.next;
		current.next = newNode;
		this.size++;
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i >= this.size) 
			throw new IndexOutOfBoundsException();
		Node<E> current = this.head;
		for (int j = 0; j < i-1; j++) {
			current = current.next;
		}
		E val = current.next.data;
		current.next = current.next.next;
		this.size--;
		return val;
	}
	
}
