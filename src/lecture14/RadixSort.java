package lecture14;

import java.util.Random;

import lecture10.LinkedQueue;
import lecture10.Queue;
import lecture12.ArrayList;

public class RadixSort {

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		Integer[] a = new Integer[50];
		for (int i = 0; i < a.length; i++)
			a[i] = rand.nextInt(1000) - 500;
		
		print(a);
		
		radixSort2(a);
		
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
	private static Integer absMax(Integer[] arr){
		Integer max = 0;
		for (int i = 0; i < arr.length; i++) {
			if (Math.abs(arr[i]) > max) max = Math.abs(arr[i]);
		}
		return max;
	}
	//hw
	//#1
	// radix sort with mix of positive and negative integers
	public static void radixSort2(Integer[] arr){
		int numPosValues = numPos_NegValues(arr)[0];
		int numNegValues = numPos_NegValues(arr)[1];

		//2 arrays that will contains the positive and negative values separately
		Integer[] posValues = new Integer[numPosValues];
		Integer[] negValues = new Integer[numNegValues];

		//filling the arrays accordingly
		int i = 0,j=0,k=0;
		while(k < arr.length){
			if(arr[k] > 0) posValues[i++] = arr[k++];
			else negValues[j++] = arr[k++];
		}
		//sort the positive value array with the positive values of arr
		radixSort(posValues);
		//negate the all the values of the negative values array
		negate(negValues);
		//sort negative value array (currently filled with positive values
		radixSort(negValues);

		//insert the (negative) values of the negative values array from last to first
		// and then insert the positive values from the sorted positive value array
		i = 0;
		j=negValues.length - 1;
		k=0;
		while(k < negValues.length){
			arr[k++] = (-1) * negValues[j--];
		}
		while(k < arr.length){
			arr[k++] = posValues[i++];
		}
	}
	private static void negate(Integer[] arr){
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (-1) * arr[i];
		}
	}
	private static Integer[] numPos_NegValues(Integer[] arr){
		Integer[] result = new Integer[2];
		result[0] = 0;
		result[1] = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] < 0) result[1]++;
			else result[0]++;
		}
		return result;
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
