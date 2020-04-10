package lecture10;

import lecture9.Stack;
import lecture9.LinkedStack;

public class PostfixParsing {

	public static void main(String[] args) {
	
		int a = 18 + 3;
		String exp = "18 3 /";
		
		double ans = parsePostfix(exp);

		System.out.println(exp + " = " + ans);
	}
	
	public static double parsePostfix(String exp) {
		String[] symbols = exp.split(" ");
		
		Stack<Double> stack  = new LinkedStack<>();
		
		for ( String s : symbols ) {
			
			if ("+-*/".contains(s)) { //found an operator
				
				double b = stack.pop();
				double a = stack.pop();
				double c;
				
				switch (s) {
					case "+":
						c = a + b;
						break;
					case "-":
						c = a - b;
						break;
					case "*":
						c = a * b;
						break;
					case "/":
						c = a / b;
						break;
					default:
						throw new IllegalArgumentException("Invalid operator");
				}
				
				stack.push(c);
				
			} else { //found an operand
				stack.push(Double.parseDouble(s));				
			}
			
		}

		return stack.pop();
	}

}
