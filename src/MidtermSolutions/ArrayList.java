package cs313midtermSolutions;

import java.util.Comparator;
import java.util.Iterator;

public class ArrayList<E> implements List<E>, Iterable<E>, Sortable<E> {
	
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
		if (i < 0 || i >= this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
		this.data[i] = e;
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i > this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
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

	@Override
	public E get(int i) {
		if (i < 0 || i >= this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
		return (E) this.data[i];
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i >= this.size()) throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for size " + this.size());
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
	
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	public String toString() {
		if (this.size() == 0) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < this.size() - 1; i++) {
			sb.append(this.get(i) + ", ");
		}
		sb.append(this.get(this.size()-1) + "]");
		return sb.toString();
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	private void resize(int newSize) {
		Object[] newData = new Object[newSize];
		for (int i = 0; i < this.size; i++) {
			newData[i] = this.data[i];
		}
		this.data = newData;
	}

	@Override
	public void addRange(int index, E[] values) throws IndexOutOfBoundsException, NullPointerException {
		if (index > this.size()) throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + this.size());
		if (this.size() + values.length >= this.data.length) this.resize(this.data.length * 2);
		
		//shift each value between end of data and index values.length positions to the right
		for (int i = this.size() + values.length - 1; i > index + values.length -1; i--)
			this.data[i] = this.data[i-values.length];
		
		//copy values into position
		for (int i = 0; i < values.length; i++)
			this.data[i + index] = values[i];
		
		this.size += values.length;
	}

	@Override
	public Object[] removeRange(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
		if (fromIndex >= this.size() || toIndex > this.size()) throw new IndexOutOfBoundsException("Indices " + fromIndex + " to " + toIndex + " out of bounds for size " + this.size());
		
		//number of values to remove
		int count = toIndex - fromIndex;
		
		//fill array with values to be removed
		Object[] range = new Object[count];
		for (int i = 0; i < count; i++)
			range[i] = this.data[i + fromIndex];
		
		//shift values to the left to overwrite the removed values
		for (int i = fromIndex; i < this.size() - count; i++)
			this.data[i] = this.data[i + count];
		
		//optional - null remaining values larger than size()
		for (int i = this.size() - count; i < this.size(); i++)
			this.data[i] = null;
		
		this.size -= (toIndex - fromIndex);
		return range;
	}

	@Override
	public void sort(Comparator<E> comp) {
		//base case
		if (this.size() < 2) return;
		
		//divide (data is split into 2 equal-sized arraylists)
		ArrayList<E> s1 = new ArrayList<>();
		ArrayList<E> s2 = new ArrayList<>();
		int m = this.size() / 2;
		for (int i = 0, j = 0; i < this.size(); i++) {
			if (i < m) s1.add(i, (E) this.data[i]);  
			else s2.add(j++, (E) this.data[i]);
		}
		
		//conquer (recursively sort the halves)
		s1.sort(comp);
		s2.sort(comp);
		
		//combine (merge)
		int i = 0, j = 0;
		while(i + j < this.size()) {
			if (j == s2.size() || (i < s1.size() && comp.compare(s1.get(i), s2.get(j)) < 0)) {
				this.data[i+j] = s1.get(i++);
			} else {
				this.data[i+j] = s2.get(j++);
			}
		}
	}

}
