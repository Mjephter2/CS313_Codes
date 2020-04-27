package Midterm;

import java.util.Random;

public class LinkedStack<E> implements Stack<E> {

//	public static void main(String[] args) {
//		LinkedStack<Integer> testList = new LinkedStack<>();
//		Random rand = new Random();
//		for(int i = 0; i < 20; i++){
//			testList.push(rand.nextInt(100));
//		}
//		System.out.println(testList);
//		int search = rand.nextInt(100);
//		System.out.println("searching for " + search + "...: found at index " + testList.search(search));
//	}

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

	@Override
	public int search(Object o) {
		LinkedIterator<E> iterator = new LinkedIterator<>(data);
		int index = 0;
		while(iterator.hasNext()){
			index++;
			if(iterator.next() == o) return index;
		}
		return -1;
	}

	public String toString() {
		return this.data.toString();
	}

}