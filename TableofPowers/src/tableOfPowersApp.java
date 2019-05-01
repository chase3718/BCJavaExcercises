import java.util.Scanner;
import java.math.*;

public class tableOfPowersApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the table of powers app.\n");

		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

		while (run) {
			System.out.print("Enter an Integer: ");
			int num = sc.nextInt();
			
			String table = "";
			
			table += ("Number\tSquared\tCubed\n"
					+ "======\t=======\t=====\n");
			
			String row = "";
			
			for (int i = 1; i <= num; i++) {
				for (int x = 1; x <= 3; x++) {
					row += ((int)(Math.pow(i, x))+"\t");
				}
				row += "\n";
			}
			
			table += row;
			
			System.out.println(table);
			
			System.out.println();
			
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
					break;
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
