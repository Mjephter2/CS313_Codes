package cs313midtermSolutions;

import java.util.Iterator;

public class LinkedStack<E> implements Stack<E> {

	private DoublyLinkedList<E> data = new DoublyLinkedList<>();
	
	@Override
	public void push(E e) {
		this.data.addFirst(e);
	}

	@Override
	public E pop() {
		return this.data.removeFirst();
	}

	@Override
	public E top() {
		return this.data.first();
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public boolean isEmpty() {
		return this.data.isEmpty();
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	@Override
	public int search(Object o) {
		int pos = 0;
		for(E element : this.data) {
			pos++;
			if (element.equals(o)) {
				return pos;
			}
		}
		return -1;
	}

}