package Midterm;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class DoublyLinkedList<E> implements Iterable<E>, Sortable<E> {

//	public static void main(String[] args) {
//		Random rand = new Random();
//
//		DoublyLinkedList<Integer> testList = new DoublyLinkedList<>();
//		for(int i = 0; i < 20; i++){
//			testList.addLast(rand.nextInt(100));
//		}
//		System.out.println(testList);
//		testList.sort(new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return o1 - o2;
//			}
//		});
//		System.out.println(testList);
//	}
	
	private Node<E> header, trailer;
	private int size;
	
	public DoublyLinkedList() {
		this.size = 0;
		this.header = new Node<>(null, null, null);
		this.trailer = new Node<>(null, null, null);
		this.header.setNext(this.trailer);
		this.trailer.setPrev(this.header);
	}
	
	private void addBetween(E data, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<>(data, prev, next);
		prev.setNext(newNode);
		next.setPrev(newNode);
		this.size++;
	}
	
	private E remove(Node<E> n) {
		Node<E> prev = n.getPrev();
		Node<E> next = n.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		this.size--;
		return n.getData();
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public E first() {
		return this.header.getNext().getData();
	}
	
	public E last() {
		return this.trailer.getPrev().getData();
	}
	
	public void addFirst(E data) {
		this.addBetween(data, this.header, this.header.getNext());
	}
	
	public void addLast(E data) {
		this.addBetween(data, this.trailer.getPrev(), this.trailer);
	}
	
	public E removeFirst() {
		if (this.isEmpty()) return null;
		return this.remove(this.header.getNext());
	}
	
	public E removeLast() {
		if (this.isEmpty()) return null;
		return this.remove(this.trailer.getPrev());
	}
	
	public String toString() {	
		if (this.isEmpty()) return "[]";
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		Node<E> current = this.header.getNext();
		
		while(current.getNext() != this.trailer) {
			sb.append(String.format("%s, ", current.getData()));
			current = current.getNext();
		}
		sb.append(String.format("%s]", current.getData()));;
		
		return sb.toString();		
	}

	@Override
	public void sort(Comparator<E> comp) {

		//bubbleSort
		for(int i = 0; i < this.size - 1; i++){
			Node<E> current = header.getNext();
			for(int j = 0; j < this.size - 1; j++){
				if(current != null && current.getNext() != null && comp.compare(current.getData(), current.getNext().getData()) > 0){
					E temp = current.getNext().getData();
					current.getNext().setData(current.getData());
					current.setData(temp);
				}
				current = current.getNext();
			}
		}
	}


	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator<>(this);
	}
}
