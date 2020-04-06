package lecture10;

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		CircularQueue<Integer> q = new LinkedCircularQueue<>();
		
		for (int i = 0; i < 100; i++)
			q.enqueue(i);
		System.out.println(q);
		
		Random rand = new Random();
		
		while (q.size() != 1) {
			
			int n = rand.nextInt(1000);
			for (int i = 0; i < n; i++)
				q.rotate();
			q.dequeue();
		}
		
		System.out.print("Winner is: " + q.dequeue());

	}

}
