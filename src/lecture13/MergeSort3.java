package lecture13;

import java.util.Comparator;
import java.util.Random;

public class MergeSort3 {
	
	public static <T> void mergeSort(T[] s, Comparator<T> comp) {
		//uses a helper method to simplify initial arguments
		mergeSort(s, 0, s.length, comp);		
	}
	
	
	private static <T> void mergeSort(T[] s, int start, int end, Comparator<T> comp) {
		if (end - start < 2) return;
		
		//divide - O(1)
		int mid = (start + end) / 2;
		
		//conquer
		mergeSort(s, start, mid, comp);
		mergeSort(s, mid, end, comp);
		
		//combine - O(n)
		merge(s, start, end, comp);
	}

	private static <T> void merge(T[] s, int start, int end, Comparator<T> comp) {
		int mid = (start + end) / 2;
		
		//note that we still need to create additional arrays to store temporary data
		Object[] temp = new Object[end - start];
		int next = 0;
		
		int i = start, j = mid;
		while (i < mid && j < end) {
			if (comp.compare(s[i], s[j]) < 0)
				temp[next++] = s[i++];
			else 
				temp[next++] = s[j++];
		}
		while (i < mid) 
			temp[next++] = s[i++];
		while (j < end) 
			temp[next++] = s[j++];
		
		for (int k = start; k < end; k++)
			s[k] = (T) temp[k - start];
	}

}
