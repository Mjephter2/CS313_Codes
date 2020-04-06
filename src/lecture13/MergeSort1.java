package lecture13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class MergeSort1<T> {

	public static void main(String[] args) {
		Integer[] array = new Integer[5];
		Random rand = new Random();
		for(int i = 0; i < array.length; i++){
			array[i] = rand.nextInt(100);
		}
		print(array);
		mergeSort(array, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		print(array);
	}
	
	//One execution takes O(n) steps
	public static <T> void mergeSort(T[] s, Comparator<T> comp) {
		if (s.length < 2) return;
		
		//divide - O(n)
		int m = s.length / 2;
		T[] s1 = Arrays.copyOfRange(s, 0, m);
		T[] s2 = Arrays.copyOfRange(s,  m,  s.length);
		
		//conquer
		mergeSort(s1, comp);
		mergeSort(s2, comp);
		
		//combine - O(n)
		merge(s1, s2, s, comp);
	}
	
	private static <T> void merge(T[] s1, T[] s2, T[] s, Comparator<T> comp) {
		int i = 0, j = 0;
		while(i + j < s.length) {
			if (j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0)) {
				s[i+j] = s1[i++];
			} else {
				s[i+j] = s2[j++];
			}
		}
	}
	
	public static <T> void print(T[] a) {
		System.out.print("[");
		for (int i = 0; i < a.length - 1; i++)
			System.out.print(a[i] + ", ");
		System.out.println(a[a.length-1] + "]");
	}

}
