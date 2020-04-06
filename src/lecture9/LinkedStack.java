package lecture9;

import lecture6.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> data = new SinglyLinkedList<>();
	
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

}
