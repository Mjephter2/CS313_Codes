package lecture8;

import java.util.Iterator;
import lecture4.AbstractList;

public class ArrayList<E> extends AbstractList<E> implements Iterable<E> {

	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			list1.add(i);
		}
		System.out.println("Initial list1\n" + list1);

		Iterator<Integer> iter1 = list1.iterator();
		System.out.println("list1 iterator created...");
		System.out.println("Adding integer 10 to list1...");
		list1.add(0,10);
		System.out.println("list1 after adding 10\n" + list1);
		System.out.print("iter1 items after list1 change: ");
		while(iter1.hasNext()){
			System.out.print(iter1.next() + " ");
		}

	}
	
	private class ArrayIterator implements Iterator<E> {

		private int idx = 0;
		private int lastReturned = -1;
		
		@Override
		public boolean hasNext() {
			return this.idx < ArrayList.this.size();
		}

		@Override
		public E next() {
			this.lastReturned = this.idx;
			this.idx++;
			return ArrayList.this.get(this.lastReturned);
		}
		
		@Override
		public void remove() {
			if (this.lastReturned < 0) throw new IllegalStateException();
			ArrayList.this.remove(this.lastReturned);
			this.lastReturned = -1;
		}
		
	}
	
	private Object[] data;
	private int size;
	
	public ArrayList() {
		this.data = new Object[10];
		this.size = 0;
	}
	
	public ArrayList(int capacity) {
		this.data = new Object[capacity];
		this.size = 0;
	}

	@Override
	public void set(int i, E e) {
		this.data[i] = e;
	}

	@Override
	public void add(int i, E e) {
		//check if we need a larger array
		if (this.size == this.data.length) {
			this.resize(this.data.length * 2);			
		}
		
		this.size++;
		
		//shift all elements from i to last element one space to the right
		for (int j = this.size - 1; j > i; j--) {
			this.data[j] = this.data[j-1];
		}
		
		this.data[i] = e;
	}
	
	private void resize(int newSize) {
		Object[] newData = new Object[newSize];
		for (int i = 0; i < this.size; i++) {
			newData[i] = this.data[i];
		}
		this.data = newData;
	}

	@Override
	public E get(int i) {
		return (E) this.data[i];
	}

	@Override
	public E remove(int i) {
		E val = (E) this.data[i];
		for (int j = i+1; j < this.size; j++) {
			this.data[j-1] = this.data[j];
		}
		this.size--;
		return val;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	public void add(E e) {
		this.add(this.size, e);
	}
	
	public void clear() {
		this.size = 0; 
	}
	
	public boolean equals(ArrayList<E> other) {
		//quick size comparison
		if (this.size != other.size) return false;
		
		//exhaustive contents check
		for (int i = 0; i < this.size; i++) {
			if (!this.data[i].equals(other.data[i])) return false;
		}
		return true;
	}
	
	public int indexOf(E e) {
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(e)) return i;
		}
		return -1;
	}
	
	public boolean contains(E e) {
		return this.indexOf(e) != -1;
	}
	
	public boolean remove(E e) {
		int idx = this.indexOf(e);
		if (idx == -1) return false;
		else {
			this.remove(idx);
			return true;
		}
	}
	
	//shallow copy
	public ArrayList<E> clone() {
		ArrayList<E> c = new ArrayList<E>(this.data.length);
		for (int i = 0; i < this.size; i++) {
			c.add((E) this.data[i]);
		}
		return c;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

}
