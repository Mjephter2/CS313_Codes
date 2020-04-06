package lecture13;

import java.util.Comparator;

import lecture6.SinglyLinkedList;

public class MergeSort2 {
	
	public static <T> void mergeSort(SinglyLinkedList<T> s, Comparator<T> comp) {
		int n = s.size();
		if (n < 2) return;
		
		//divide - O(n)
		SinglyLinkedList<T> s1 = new SinglyLinkedList<>();
		SinglyLinkedList<T> s2 = new SinglyLinkedList<>();
		while(s1.size() < n / 2)
			s1.addLast(s.removeFirst());
		while(!s.isEmpty())
			s2.addLast(s.removeFirst());
		
		//conquer
		mergeSort(s1, comp);
		mergeSort(s2, comp);
		
		//combine - O(n)
		merge(s1, s2, s, comp);
	}
	
	private static <T> void merge(SinglyLinkedList<T> s1, SinglyLinkedList<T> s2, SinglyLinkedList<T> s, Comparator<T> comp) {
		while(!s1.isEmpty() && !s2.isEmpty()) {
			if (comp.compare(s1.first(), s2.first()) < 0) {
				s.addLast(s1.removeFirst());
			} else {
				s.addLast(s2.removeFirst());
			}
		}
		
		//one of the lists is now empty, so add the remaining elements from the other list
		while(!s1.isEmpty()) {
			s.addLast(s1.removeFirst());
		}
		while(!s2.isEmpty()) {
			s.addLast(s2.removeFirst());
		}
	}

}
