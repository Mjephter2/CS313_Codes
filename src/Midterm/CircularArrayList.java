package Midterm;

import java.util.NoSuchElementException;
import java.util.Random;

public class CircularArrayList<E> implements CircularList<E> {

	public static void main(String[] args) {
		CircularArrayList<Integer> testList = new CircularArrayList<Integer>(30);
		Random rand = new Random();
		for(int i = 0; i < 20; i++){
			testList.addFirst(rand.nextInt(100));
		}
		System.out.println(testList);
		System.out.println("First: " + testList.first());
		System.out.println("Last: " + testList.last());
		System.out.println("applying addLast");
		testList.addLast(10000);
		System.out.println("new Last: " + testList.last());
		System.out.println(testList);
		System.out.println("removeFirst: " + testList.removeFirst());
		System.out.println(testList);
		System.out.println("rotating");
		for(int i = 0; i < 2; i++) {
			testList.rotate();
		}
		System.out.println(testList);
	}

	private Object[] data;
	private int size, first;

	public CircularArrayList(int capacity){
		data = new Object[capacity];
		size = 0;
		first = -1;
	}
	public CircularArrayList(){
		data = new Object[10];
		size = 0;
		first = -1;
	}
	
	/**
	 * Creates a new array with the given capacity and copies all of the elements from this list into that array.
	 * Used when the capacity of this list's array is no longer sufficient for the number of elements.
	 * 
	 * //@param newSize new capacity of the underlying array
	 */
	private void resize(int newCapacity) {
		Object[] newArray = new Object[newCapacity];
		for(int i = 0; i < this.size; i++){
			newArray[i] = data[(first + i) % data.length];
		}
		data = newArray;
		first = 0;
	}

	@Override
	public E first() {
		if(this.isEmpty()) return null;
		return (E)data[first];
	}

	@Override
	public E last() {
		if(this.isEmpty()) return null;
		return (E)data[(first + size - 1 + data.length) % data.length];
	}

	@Override
	public void addFirst(E e) {
		if(size == data.length) this.resize(data.length * 2);
		first = (first - 1 + data.length) % data.length;
		data[first] = e;
		this.size++;
	}

	@Override
	public void addLast(E e) {
		if(size == data.length) this.resize(data.length * 2);
		if(size == 0) this.addFirst(e);
		else data[(first + size) % data.length] = e;
		this.size++;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if(this.isEmpty()) throw new NoSuchElementException();
		E value = this.first();
		data[first] = null;

		if(size == 1) {
			first = -1;
		}else {
			first = (first + 1) % data.length;
		}
		this.size--;
		return value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return data.length == 0;
	}

	@Override
	public void rotate() {
		E value = this.first();
		data[first] = null;
		this.addLast(value);
		this.size--;
		first = (first + 1) % data.length;
	}

	public String toString(){
		if(this.isEmpty()) return "[Empty]";
		StringBuilder sb = new StringBuilder("Size = " + this.size +" [");

		for(int i = 0; i <= this.size() - 2; i++){
			sb.append(data[(first + i) % data.length] + ", ");
		}
		sb.append(data[(first + size - 1 + data.length) % data.length] + "]");
		return sb.toString();
	}
}
