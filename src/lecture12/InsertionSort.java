package lecture12;

import lecture7.LinkedPositionalList;
import lecture7.Position;
import lecture7.PositionalList;

import java.util.Comparator;
import java.util.Random;

public class InsertionSort {

	public static void main(String[] args) {
		
		//Anonymous class.  Could also define this in its own file.
		//This Comparator defines a compare method that sorts in descending order
		Comparator<Integer> comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		};
		
		//generic sort method
		
		Random rand = new Random();
		Integer[] data1 = new Integer[10];
		
		for (int i = 0; i < 10; i++) {
			data1[i] = rand.nextInt(100);
		}
		
		print(data1);
		
		insertionSort(data1, comp);
		
		print(data1);
		
		//ArrayList with generic sort instance method
		System.out.println();
		
		ArrayList<Integer> data2 = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			data2.add(rand.nextInt(100));
		}
		
		System.out.println(data2);
		
		data2.sort(comp);
		
		System.out.println(data2);

		System.out.println("**************");
		PositionalList<Integer> list1 = new LinkedPositionalList<>();
		for(int i = 0; i < 20; i++){
			list1.addLast(rand.nextInt(20));
		}
		System.out.println("Before sort: " + list1);
		insertionSort(list1);
		System.out.println("After sort: " + list1);
		
	}
	
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int curr = arr[i];
			int j = i;
			while(j > 0 && arr[j-1] > curr) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = curr;			
		}
	}
	
	public static <T> void insertionSort(T[] arr, Comparator<T> comp) {
		for (int i = 1; i < arr.length; i++) {
			T curr = arr[i];
			int j = i;
			while(j > 0 && comp.compare(arr[j-1], curr) > 0) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = curr;			
		}
	}
	public static void insertionSort(PositionalList<Integer> list){
		Position<Integer> marker = list.first();
		while(marker != list.last()){
			Position<Integer> pivot = list.after(marker);
			int value = pivot.getData();
			if(value > marker.getData()){
				marker = pivot;
			}else{
				Position<Integer> walk = marker;
				while(walk != list.first() && list.before(walk).getData() > value){
					walk = list.before(walk);
				}
				list.remove(pivot);
				list.addBefore(walk, value);
			}
		}
	}
	
	public static <T> void print(T[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

}
