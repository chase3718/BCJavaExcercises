import java.util.Scanner;

public class commDivCalc {
	public static void main (String[] args) {
		System.out.println("Greates Common Divisor Finder\n");

		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

		while (run) {
			System.out.print("Enter first number:   ");
			int x = sc.nextInt();
			
			System.out.print("Enter second Number:  ");
			int y = sc.nextInt();
			
			int z = 0;
			
			while (x > 0) {
				//System.out.println("Run");
				if (x < y) {
					y = y - x;
					//System.out.println(x);
				} else if (x > y) {
					z = y;
					y = x;
					x = z;
					//System.out.println("x: " + x + "\n" + "y: " + y);
				} else {
					break;
				}
			}
			
			System.out.println("Greatest common divisor: " + y);
			
			
			sc.nextLine();
			System.out.print("Continue? (y/n) ");
			choice = sc.nextLine();
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
					choice = sc.nextLine();
					System.out.println();
				}
			}
		}
	}
}
