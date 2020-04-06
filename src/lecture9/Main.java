package lecture9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String openings = "({[<";
		String closings = ")}]>";
		
		Stack<Character> stack = new LinkedStack<>();
		
		try {
			Scanner input = new Scanner(new File(args[0]));
			
			String line;
			
			while(input.hasNextLine()) {
				
				line = input.nextLine();
				
				for (int i = 0; i < line.length(); i++) {
					
					char c = line.charAt(i);
					
					if (openings.indexOf(c) >= 0) { //found an opening char
						stack.push(c);
						System.out.println("pushed " + c);
					} else if (closings.indexOf(c) >= 0) { //found a closing char
						if (stack.isEmpty()) throw new Exception("Empty stack");
						char popped = stack.pop();
						if (openings.indexOf(popped) != closings.indexOf(c))
							throw new Exception("mismatch char");
					}
				}
			}
			System.out.println("No errors");
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
