package app;

import business.Calculation;
import ui.Console;

public class CalculatorApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Calculator App");

		Console in = new Console();

		do {

			Calculation calc = new Calculation(Calculation.getStringCheckForErrors(in.getString(
					"\nEnter a function separated by spaces.\n\nPermitted Operators (+,-,*,/,%)\n\nEnter: ")));

			if (!calc.getCalc().equalsIgnoreCase("INVALID")) {
				calc.splitIntoParts();
				System.out.println(calc.doCalculation());
			} else {
				System.out.println("Invalid input.");
			}
		} while (in.getString("\nContinue? (y/n) ", true, "y", "n").equalsIgnoreCase("y"));

		System.out.println("\nBye");
	}
}
