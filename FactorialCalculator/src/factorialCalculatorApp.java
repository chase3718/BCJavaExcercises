import java.util.Scanner;

public class factorialCalculatorApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the Factorial Calculator\n");
		
		/** Make sure to import java.util.scanner. Must have a Scanner called sc.**/

		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

		while (run) {
			
			System.out.print("Enter an integer that's greater than 0 and less than 10: ");
			int num = sc.nextInt();
			int fac = num;
			
			for (int i = 1; i < num; i++) {
				fac *= i;
			}
			
			System.out.println("The factorial of " + num + " is " + fac +"\n");
			
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
	}
}
