package lecture10;

import lecture6.CircularlyLinkedList;

public class LinkedCircularQueue<E> implements CircularQueue<E> {

	private CircularlyLinkedList<E> data = new CircularlyLinkedList<>();
	
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

	@Override
	public void rotate() {
		this.data.rotate();
	}

	public String toString(){
		return this.data.toString();
	}
	
}
