package lecture13;

import java.util.Comparator;
import java.util.Random;

import lecture6.SinglyLinkedList;

public class Main {

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		Integer[] arr = new Integer[8];
		SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
		
		for (int i = 0; i < arr.length; i++) {
			int val = rand.nextInt(20);
			arr[i] = val;
			list.addLast(val);
		}
		
		print(arr);
		//System.out.println(list);
		
		Comparator<Integer> comp = new Comparator<Integer> () {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		
		//sort
		MergeSort1.mergeSort(arr, comp);
		
		print(arr);
		//System.out.println(list);
	}
	
	public static <T> void print(T[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length - 1; i++)
			System.out.print(arr[i] + ", ");
		System.out.println(arr[arr.length-1] + "]");
	}

}
