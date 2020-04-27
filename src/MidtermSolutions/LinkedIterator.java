package cs313midtermSolutions;

import java.util.Iterator;

public class LinkedIterator<E> implements Iterator<E> {
	
	private Node<E> cursor;
	private Node<E> lastReturned = null;
	
	public LinkedIterator(Node<E> header) {
		this.cursor = header.getNext();
	}
	
	@Override
	public boolean hasNext() {
		/** Need to check if getNext() != null since a DoublyLinkedList has a trailer node 
		 */
		return this.cursor.getNext() != null;
	}

	@Override
	public E next() {
		this.lastReturned = this.cursor;
		this.cursor = this.cursor.getNext();
		return this.lastReturned.getData();
	}
	
	@Override
	public void remove() {
		//This is not complete since we can't update the size of the original list
		if (this.lastReturned == null) throw new IllegalStateException();
		Node<E> prev = this.lastReturned.getPrev();
		Node<E> next = this.lastReturned.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		this.lastReturned = null;
	}

}
