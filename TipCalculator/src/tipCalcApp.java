import java.math.*;
import java.util.Scanner;

public class tipCalcApp {
	public static void main (String[] args) {
		System.out.println("Tip Calculator\n");
		
		/** Make sure to import java.util.scanner. Must have a Scanner called sc.**/

		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

		while (run) {
			
			System.out.print("Cost of meal: ");
			BigDecimal cost = new BigDecimal(sc.nextDouble()).setScale(2, RoundingMode.HALF_UP);
			
			for (double i = 15; i <= 25; i += 5) {
				System.out.println(i + "%");
				
				BigDecimal per = new BigDecimal(i/100).setScale(2, RoundingMode.HALF_UP);
				
				System.out.println("Tip amount:      " + cost.multiply(per).setScale(2, RoundingMode.HALF_UP));
				System.out.println("Total Amount:    " + cost.add(cost.multiply(per)).setScale(2, RoundingMode.HALF_UP) + "\n");
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
	}
}
