package lecture14;

import java.util.Random;

import lecture10.LinkedQueue;
import lecture10.Queue;
import lecture12.ArrayList;

public class RadixSort {

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		Integer[] a = new Integer[8];
		for (int i = 0; i < a.length; i++)
			a[i] = rand.nextInt(1000);
		
		print(a);
		
		radixSort(a);
		
		print(a);
	}
	
	public static void radixSort(Integer[] arr) {
		//find maximum value
		int max = max(arr);
		
		//find number of repetitions needed
		int digits = countDigits(max);
		
		//initialize radix "buckets" (always size 10 for decimal integers)
		//each bucket contains an empty Queue
		ArrayList<Queue<Integer>> radix = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			radix.add(new LinkedQueue<>());
		
		//use a Queue to overall sequence between loops
		Queue<Integer> q = new LinkedQueue<>();
		for (int i = 0; i < arr.length; i++)
			q.enqueue(arr[i]);
		
		//repeat based on the number of digits
		for (int digit = 0; digit < digits; digit++) {
			
			//sort values from queue into radix buckets by digit
			while(!q.isEmpty()) {
				Integer val = q.dequeue();
				int bucketIdx = hash(val, digit);
				radix.get(bucketIdx).enqueue(val); 
			}
			
			//move all values from radix buckets back into original queue
			for (int i = 0; i < 10; i++) {
				while(!radix.get(i).isEmpty()) {
					q.enqueue(radix.get(i).dequeue());
				}
			}
		}
		
		//Queue now contains sorted values - copy to original array
		for (int i = 0; i < arr.length; i++)
			arr[i] = q.dequeue();
		
	}
	
	private static Integer max(Integer[] arr) {
		Integer max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) max = arr[i];
		}
		return max;
	}
	
	private static int countDigits(Integer n) {
		int digits = 0;
		while(n != 0) {
			n /= 10;
			digits++;
		}
		return digits;
	}
	
	private static int hash(Integer value, int digit) {
		int decimal = (int) Math.pow(10, digit);
		int h = (value / decimal) % 10;
		return h;
	}
	
	public static <T> void print(T[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length - 1; i++)
			System.out.print(arr[i] + ", ");
		System.out.println(arr[arr.length-1] + "]");
	}
}
