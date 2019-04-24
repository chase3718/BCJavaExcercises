import java.util.Scanner;
import java.math.*;
import java.text.NumberFormat;

public class InterestCalculatorApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the Interest Calculator App!");
		
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

		NumberFormat currency = NumberFormat.getCurrencyInstance();
		NumberFormat percent = NumberFormat.getPercentInstance();
		
		percent.setMaximumFractionDigits(3);
		
		while (run) {
			System.out.print("Enter loan amount:   ");
			BigDecimal loanAmount = new BigDecimal(sc.nextDouble());
			
			System.out.print("Enter Interest Rate: ");
			BigDecimal intRate = new BigDecimal(sc.nextDouble());
			
			BigDecimal interest = loanAmount.multiply(intRate).setScale(2, RoundingMode.HALF_UP);
			
			System.out.println("Loan amount:         " + currency.format(loanAmount));
			System.out.println("Interest Rate:       " + percent.format(intRate));
			System.out.println("Interest:            " + currency.format(interest));
			
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
