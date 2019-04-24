import java.util.Scanner;

public class CalculatorApp {
	public static void main(String[] args) {
		System.out.println(
				"Welcome to the calculator \nThe form of this calculator is 'a [operator] b = x. (Ex. a = 8, b = 4, a / b = c, c = 2");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		double a;
		double b;
		double x = 0;
		String c;
		boolean run = true;
		boolean cont = true;
		String choice = "y";
		
		while (run) {
		
			System.out.print("a = ");
			a = sc.nextDouble();
			System.out.println();
			System.out.print("b = ");
			b = sc.nextDouble();
			System.out.println();
			System.out.print("Chose an operator (+, -, x, /) ");
			c = sc.next();
			System.out.println();
			System.out.println(a + " " + c + " " + b + " = c");
			System.out.println();
			
			if (c.equals("+")) {
				x = a + b;
			} else if (c.equals("-")) {
				x = a - b;
			} else if (c.equals("x")) {
				x = a * b;
			} else if (c.equals("/")) {
				x = a / b;
			} else {
				System.out.println("That dosen't make sense.");
				System.out.println();
				run = false;
			}
			
			if (run) {
				System.out.println("c = " + x);
				System.out.println();
			}
			
			System.out.print("Continue? (y/n) ");
			choice = sc.next();
			System.out.println();

			cont = true;

			while (cont) {
				if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
					run = false;
					sc.close();
					System.out.println("OK, Goodbye.");
					cont = false;
				} else if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
					cont = false;
				} else {
					System.out.print("Must be in the form of y/n or yes/no.\n\nContinue? (y/n) ");
					choice = sc.next();
					System.out.println();
				}
			}
		}

		System.out.println("Cya");
	}
}