package lecture7;

import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) {
		
		LinkedPositionalList<Integer> l1 = new LinkedPositionalList<>();
		for (int i = 1; i < 10; i++) {
			l1.addLast(i);
			//System.out.println(l);
		}

		LinkedPositionalList<Integer> l2 = new LinkedPositionalList<>();
		for (int i = 10; i < 20; i++) {
			l2.addLast(i);
			//System.out.println(l);
		}

		System.out.println("l1: " + l1);
		System.out.println("l2: " + l2); //implement a toString method

		Position<Integer> position1 = l1.after(l1.first());
		Position<Integer> position2 = l2.after(l2.first());

		System.out.println(l2.before(position1).getData());

//		Position<Integer> current = l.first();
//		while(true) {
//			System.out.println(current.getData());
//			current = l.after(current);
//			if (current == null) break;
//		}
		
		
	}

}
