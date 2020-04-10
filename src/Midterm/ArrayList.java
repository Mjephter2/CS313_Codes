package Midterm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


public class ArrayList<E> implements List<E>, Iterable<E>, Sortable<E> {

//	public static void main(String[] args) {
//		ArrayList<Integer> testList = new ArrayList<>();
//		Random rand = new Random();
//		for(int i = 0; i < 30; i++){
//			testList.add(i,rand.nextInt(100) + 100);
//		}
//
//		System.out.println(testList);
//		testList.sort(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return o1 - o2;
//			}
//		});
//		System.out.println(testList);
//	}


	private void mergeSort(E[] array, Comparator<E> comp) {

		if (array.length < 2) return;
		//divide - O(n)
		int m = array.length / 2;
		E[] s1 = Arrays.copyOfRange(array, 0, m);
		E[] s2 = Arrays.copyOfRange(array,  m,  array.length);

		//conquer
		mergeSort(s1, comp);
		mergeSort(s2, comp);

		//combine - O(n)
		merge(s1, s2, array, comp);
	}
	private void merge(E[] s1, E[] s2, E[] s, Comparator<E> comp){
		int i = 0, j = 0;
		while(i + j < s.length) {
			if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
				s[i+j] = s1[i++];
			} else {
				s[i+j] = s2[j++];
			}
		}
	}
	private void mergeArrays(E[] fromArray, E[] toArray){
		for(int i = 0; i < fromArray.length; i++){
			toArray[i] = fromArray[i];
		}
	}

	@Override
	public void sort(Comparator<E> comp) {
		if(this.size == data.length){
			mergeSort((E[]) data, comp);
		}else{
			E[] toSort = (E[]) Arrays.copyOfRange(data, 0,this.size);
			mergeSort(toSort, comp);
			mergeArrays(toSort, (E[]) data);
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

	@Override
	public void addRange(int index, E[] values) throws IndexOutOfBoundsException, NullPointerException {
		if(index < 0 || index > this.size) throw new IndexOutOfBoundsException();
		if(values == null) throw new NullPointerException();
		if(size == this.data.length) this.resize(data.length * 2);
		for(int i = values.length - 1; i >= 0; i--){
			this.add(index, values[i]);
		}
	}

	@Override
	public Object[] removeRange(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
		if(fromIndex == toIndex) return null;
		if(fromIndex < 0 || fromIndex >= size() || toIndex > size() || toIndex < fromIndex) throw new IndexOutOfBoundsException();
		int returnArraySize = toIndex - fromIndex + 1;
		Object[] returnArray = new Object[returnArraySize];
		for(int i = 0; i < returnArraySize; i++){
			returnArray[i] = this.remove(fromIndex);
		}
		return returnArray;
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

}
