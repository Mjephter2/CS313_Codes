package lecture6;

import lecture4.ArrayList;

public class Experiment {

	public static int N = 1000000;
	
	public static void main(String[] args) {
		// uncomment one line to run that experiment
		arrayListTest(N);
		//linkedListTest(N);
	}
	
	public static void arrayListTest(int n) {
		ArrayList<Integer> a = new ArrayList<>();
		
		System.out.println("begin filling list");
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			a.add(a.size(), i);
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.printf("end filling list\ntotal time: %d\n", time);
		
		System.out.println("begin summation");
		start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int val = a.get(i);
			if (val % 3 == 0) sum+=val;
		}
		end = System.currentTimeMillis();
		time = end - start;
		System.out.printf("end summation\ntotal time: %d\n", time);
		
		System.out.printf("sum is %d\n", sum);
	}
	
	public static void linkedListTest(int n) {
		SinglyLinkedList<Integer> a = new SinglyLinkedList<>();
		
		System.out.println("begin filling list");
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			a.addLast(i);
		}
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.printf("end filling list\ntotal time: %d\n", time);
		
		System.out.println("begin summation");
		start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int val = a.get(i);
			if (val % 3 == 0) sum+=val;
		}
		end = System.currentTimeMillis();
		time = end - start;
		System.out.printf("end summation\ntotal time: %d\n", time);
		
		System.out.printf("sum is %d\n", sum);
	}
}
