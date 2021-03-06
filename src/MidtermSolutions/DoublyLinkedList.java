package cs313midtermSolutions;

import java.util.Comparator;
import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E>, Sortable<E> {
	
	private Node<E> header, trailer;
	private int size;
	
	public DoublyLinkedList() {
		this.size = 0;
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, null, null);
		this.header.setNext(this.trailer);
		this.trailer.setPrev(this.header);
	}
	
	private void addBetween(E data, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<>(data, prev, next);
		prev.setNext(newNode);
		next.setPrev(newNode);
		this.size++;
	}
	
	private E remove(Node<E> n) {
		Node<E> prev = n.getPrev();
		Node<E> next = n.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		this.size--;
		return n.getData();
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public E first() {
		return this.header.getNext().getData();
	}
	
	public E last() {
		return this.trailer.getPrev().getData();
	}
	
	public void addFirst(E data) {
		this.addBetween(data, this.header, this.header.getNext());
	}
	
	public void addLast(E data) {
		this.addBetween(data, this.trailer.getPrev(), this.trailer);
	}
	
	public E removeFirst() {
		if (this.isEmpty()) return null;
		return this.remove(this.header.getNext());
	}
	
	public E removeLast() {
		if (this.isEmpty()) return null;
		return this.remove(this.trailer.getPrev());
	}
	
	public String toString() {	
		if (this.isEmpty()) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		Node<E> current = this.header.getNext();
		
		while(current.getNext() != this.trailer) {
			sb.append(String.format("%s, ", current.getData()));
			current = current.getNext();
		}
		sb.append(String.format("%s]", current.getData()));;
		
		return sb.toString();		
	}

	@Override
	public Iterator<E> iterator() {
		//need to pass a reference to the starting point for the iterator
		return new LinkedIterator<E>(this.header);
	}

	@Override
	public void sort(Comparator<E> comp) {

		//The sorted portion is initially made up of only the first element
		Node<E> lastSorted = this.header.getNext();
		
		while(lastSorted.getNext() != this.trailer) {
			
			//if next element is larger than the lastSorted, then it is already in order so skip it
			if (comp.compare(lastSorted.getData(), lastSorted.getNext().getData()) <= 0) {
				lastSorted = lastSorted.getNext();
				continue;
			}
			
			//get next element to be sorted
			Node<E> nextUnsorted = lastSorted.getNext();
			
			//find correct position for the next unsorted element
			Node<E> cursor = lastSorted;
			while(cursor != this.header && comp.compare(cursor.getData(),nextUnsorted.getData()) > 0) {
				cursor = cursor.getPrev();
			}
			
			//insert at correct position
			E val = this.remove(nextUnsorted);
			this.addBetween(val, cursor, cursor.getNext());
			
		}
	}
	
}
