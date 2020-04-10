package lecture10;

import lecture6.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

	private SinglyLinkedList<E> data = new SinglyLinkedList<>();
	
	@Override
	public void enqueue(E e) {
		this.data.addLast(e);
	}

	@Override
	public E dequeue() {
		return this.data.removeFirst();
	}

	@Override
	public E first() {
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

	public String toString(){
		return data.toString();
	}

}
