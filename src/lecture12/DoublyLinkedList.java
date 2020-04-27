package lecture12;

import java.util.Comparator;
import java.util.Random;

public class DoublyLinkedList<E> {

	public static void main(String[] args) {
		DoublyLinkedList<Character> charList = new DoublyLinkedList<>();
		Random rand = new Random();

	}

	private static class Node<E> {
		
		private E data;
		private Node<E> prev, next;
		
		public Node(E data, Node<E> prev, Node<E> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		public E getData(){
			return data;
		}
		
	}
	
	private Node<E> header, trailer;
	private int size;
	
	public DoublyLinkedList() {
		this.size = 0;
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, null, null);
		this.header.next = this.trailer;
		this.trailer.prev = this.header;
	}

	// O(1)
	private void addBetween(E data, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<>(data, prev, next);
		prev.next = newNode;
		next.prev = newNode;
		this.size++;
	}

	// O(1)
	private E remove(Node<E> n) {
		Node<E> prev = n.prev;
		Node<E> next = n.next;
		prev.next = next;
		next.prev = prev;
		this.size--;
		return n.data;
	}

	// O(1)
	public int size() {
		return this.size;
	}

	// O(1)
	public boolean isEmpty() {
		return this.size == 0;
	}

	// O(1)
	public E first() {
		return this.header.next.data;
	}

	// O(1)
	public E last() {
		return this.trailer.prev.data;
	}

	// O(1)
	public void addFirst(E data) {
		this.addBetween(data, this.header, this.header.next);
	}

	// O(1)
	public void addLast(E data) {
		this.addBetween(data, this.trailer.prev, this.trailer);
	}

	// O(1)
	public E removeFirst() {
		if (this.isEmpty()) return null;
		return this.remove(this.header.next);
	}

	// O(1)
	public E removeLast() {
		if (this.isEmpty()) return null;
		return this.remove(this.trailer.prev);
	}

	public void sort(Comparator<? super E> comparator) {
//		if(size <= 1) return;
//		Node<E> current = this.header.next.next;
//		Node<E> previous = null;
//		while(current != trailer){
//			E currentData = current.getData();
//			previous = current.prev;
//			while(previous != header && comparator.compare(previous.getData(), current.getData()) > 0 ){
//				this.addBetween(current.getData(), previous.prev,);
//			}
//
//			current = current.next;
//		}

	}

	public String toString(){
		if(size == 0) return "[]";
		StringBuilder s = new StringBuilder();
		Node<E> currentNode = header.next;
		s.append("[");
		for(int i = 0; i <= size -2; i++){
			s.append(currentNode.getData() + ", ");
			currentNode = currentNode.next;
		}
		s.append(currentNode.getData());
		s.append("]");
		return s.toString();
	}
	
}
