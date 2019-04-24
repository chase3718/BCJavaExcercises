import java.util.Scanner;

public class RectangleCalcApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the rectangle calculator.\n");

		Scanner sc = new Scanner(System.in);

		double width;
		double height;
		double area;
		double perimeter;
		boolean run = true;
		boolean cont = true;
		String choice = "y";

		while (run) {
			System.out.print("Enter width: ");

			width = sc.nextDouble();

			System.out.print("Enter height: ");

			height = sc.nextDouble();

			area = width * height;
			perimeter = 2 * (width + height);

			System.out.println("Area:      " + area);
			System.out.println("Perimeter: " + perimeter);

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
