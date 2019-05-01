import java.util.Scanner;

public class DataValidationApp {
	public static double getDoubleWithinRange(Scanner sc, String prompt, double min, double max) {
		boolean isValid = false;
		double n = 0;
		while (!isValid) {
			isValid = false;
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				n = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid decimal value. Try Again.");
				sc.nextLine();
			}
			// sc.nextLine();

			if (isValid && n <= min) {
				isValid = false;
				System.out.println("Error! Value must be greater than " + min + ".");
				sc.nextLine();

			} else if (isValid && n >= max) {
				isValid = false;
				System.out.println("Error! Value must be less than " + max + ".");
				sc.nextLine();

			}
		}
		// sc.nextLine();
		return n;
	}

	public static boolean doContinue() {
		
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

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
		sc.nextLine();
		return run;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the Area and Perimeter Calculator.\n");

		Scanner sc = new Scanner(System.in);

		boolean run = true;

		while (run) {

			double length = getDoubleWithinRange(sc, "Enter length: ", 0, 1000000);
			double width = getDoubleWithinRange(sc, "Enter width: ", 0, 1000000);

			System.out.println("Area: " + length * width);
			System.out.println("Perimeter: " + 2 * (length + width));

			run = doContinue();
			sc.nextLine();
		}
	}
}
