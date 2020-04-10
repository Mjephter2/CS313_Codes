package lecture11;

public class PostfixParsing_hw3 {

	public static void main(String[] args) {
	
		int a = 18 + 3;
		String exp = "2 3 + 5 /";
		
		double ans = parsePostfix(exp);

		System.out.println(exp + " = " + ans);
	}
	
	public static double parsePostfix(String exp) {
		String[] symbols = exp.split(" ");
		
		Deque<Double> deque  = new LinkedDeque<>();
		
		for ( String s : symbols ) {
			
			if ("+-*/".contains(s)) { //found an operator
				
				double b = deque.removeFirst();
				double a = deque.removeFirst();
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
				
				deque.addFirst(c);
				
			} else { //found an operand
				deque.addFirst(Double.parseDouble(s));
			}
			
		}

		return deque.removeFirst();
	}

}
