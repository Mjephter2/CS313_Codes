package lecture11;

import lecture7.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {

	private DoublyLinkedList<E> data = new DoublyLinkedList<>();
	
	@Override
	public void addFirst(E e) {
		this.data.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		this.data.addLast(e);
	}

	@Override
	public E removeFirst() {
		return this.data.removeFirst();
	}

	@Override
	public E removeLast() {
		return this.data.removeLast();
	}

	@Override
	public E first() {
		return this.data.first();
	}

	@Override
	public E last() {
		return this.data.last();
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public boolean isEmpty() {
		return this.data.isEmpty();
	}

}
