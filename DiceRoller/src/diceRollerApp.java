import java.util.Scanner;

public class diceRollerApp {

	public static int roll() {
		int roll = (int) (Math.random() * 6) + 1;
		return roll;
	}

	public static String doDiceRoll() {
		int dice1 = roll();
		int dice2 = roll();

		if (dice1 == dice2 && dice1 == 1) {
			return ("Die 1: " + dice1 + "\nDie 2: " + dice2 + "\nTotal: " + (dice1 + dice2) + "\nSnake Eyes!\n");
		} else if (dice1 == dice2 && dice1 == 6) {
			return ("Die 1: " + dice1 + "\nDie 2: " + dice2 + "\nTotal: " + (dice1 + dice2) + "\nBoxcars!\n");
		} else {
			return ("Die 1: " + dice1 + "\nDie 2: " + dice2 + "\nTotal: " + (dice1 + dice2) + "\n");
		}
	}

	public static void main(String[] args) {
		System.out.println("Dice Roller\n");

		Scanner sc = new Scanner(System.in);

		System.out.print("Roll the dice? (y/n) ");
		String choice = sc.nextLine();

		boolean cont = true;
		boolean run = true;

		while (cont) {
			if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
				run = false;
				sc.close();
				System.out.println("OK, Goodbye.");
				cont = false;
			} else if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
				cont = false;
			} else {
				System.out.print("Must be in the form of y/n or yes/no.\n\nRoll the dice? (y/n) ");
				choice = sc.next();
				System.out.println();
			}
		}

		cont = true;

		while (run) {

			System.out.print(doDiceRoll());

			System.out.print("Roll again? (y/n) ");
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
					System.out.print("Must be in the form of y/n or yes/no.\n\nRoll again? (y/n) ");
					choice = sc.next();
					System.out.println();
				}
			}
		}

	}
}
