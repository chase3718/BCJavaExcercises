import java.util.Scanner;

public class GradeConverterApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the grade converter app.");

		Scanner sc = new Scanner(System.in);

		double grade;
		boolean run = true;
		boolean cont = true;
		String choice;

		while (run) {
			System.out.print("Enter numerical grade: ");
			grade = sc.nextDouble();
			System.out.println();

			if (grade > 100 || grade < 0) {
				System.out.println("Invalid Grade");
				System.out.println();
			} else if (grade >= 88) {
				System.out.println("Letter Grade: A");
				System.out.println();
			} else if (grade >= 80) {
				System.out.println("Letter Grade: B");
				System.out.println();
			} else if (grade >= 67) {
				System.out.println("Letter Grade: C");
				System.out.println();
			} else if (grade >= 60) {
				System.out.println("Letter Grade: D");
				System.out.println();
			} else {
				System.out.println("Letter Grade: F");
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
	}
}
