package cs313midtermSolutions;

import java.util.NoSuchElementException;

//This class is very similar conceptually to the ArrayQueue from lecture 10

public class CircularArrayList<E> implements CircularList<E> {

	private Object[] data;
	private int first, size;
	
	public CircularArrayList() {
		this.data = new Object[10];
		this.first = 0;
		this.size = 0;
	}
	
	/**
	 * Creates a new array with the given capacity and copies all of the elements from this list into that array.
	 * Used when the capacity of this list's array is no longer sufficient for the number of elements.
	 * 
	 * @param newSize	new capacity of the underlying array
	 */
	private void resize(int newCapacity) {
		Object[] newData = new Object[newCapacity];
		for (int i = 0; i < this.size; i++) {
			newData[i] = this.data[(i + first) % this.data.length];
		}
		this.data = newData;
		this.first = 0;
	}
	
	public String toString() {
		if (this.size() == 0) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < this.size() - 1; i++) {
			sb.append(this.data[(i + this.first) % this.data.length] + ", ");
		}
		sb.append(this.data[(this.first + this.size - 1) % this.data.length] + "]");
		return sb.toString();
	}

	@Override
	public E first() {
		return (E) this.data[this.first];
	}

	@Override
	public E last() {
		int last = (this.first + this.size - 1 + this.data.length) % this.data.length;
		return (E) this.data[last];
	}

	@Override
	public void addFirst(E e) {
		if (this.size == this.data.length) this.resize(this.data.length * 2);
		this.first = (this.first - 1 + this.data.length) % this.data.length;
		this.data[this.first] = e;
		this.size++;
	}

	@Override
	public void addLast(E e) {
		if (this.size == this.data.length) this.resize(this.data.length * 2);
		int last = (this.first + this.size) % this.data.length;
		this.data[last] = e;
		this.size++;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (this.isEmpty()) throw new NoSuchElementException("List is empty");
		E val = (E) this.data[this.first];
		this.data[this.first] = null;
		this.first = (this.first + 1) % this.data.length;
		this.size--;
		return val;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void rotate() {
		E val = (E) this.data[this.first];
		this.data[this.first] = null;
		int last = (this.first + this.size) % this.data.length;
		this.data[last] = val;
		this.first = (this.first + 1) % this.data.length;
	}
	
}
