package williams.app;
import williams.account.CheckingAccount;
import williams.account.SavingsAccount;
import williams.presentation.Console;

public class AccountBalanceCalcApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Account Application\n");

		String choice = "y";
		Console in = new Console();

		System.out.println("Starting Balances");
		CheckingAccount chk = new CheckingAccount(1000);
		SavingsAccount svg = new SavingsAccount(1000);
		
		System.out.println("Checking: " + chk.getBalanceFormated());
		System.out.println("Savings: " + svg.getBalanceFormated());
		
		while (choice.equalsIgnoreCase("y")) {
			System.out.println("\nEnter the transactions for the month\n");
			String actn = in.getStringOptions("Withdrawal or Deposit? (w/d) ", "w", "d", true);
			String accnt = in.getStringOptions("Checking or Savings? (c/s) ", "c", "s", true);
			
			if (accnt.equalsIgnoreCase("c")) {
				if (actn.equalsIgnoreCase("w")) {
					chk.withdraw(in.getDouble("Amount? "));
				} else {
					chk.deposit(in.getDouble("Amount? "));
				}
			} else {
				if (actn.equalsIgnoreCase("w")) {
					svg.withdraw(in.getDouble("Amount? "));
				} else {
					svg.deposit(in.getDouble("Amount? "));
				}
			}
			
			choice = in.getStringOptions("\nContinue? (y/n) ", "y", "n", true);
		}
		
		svg.setMonthlyInterestPayment();
		
		System.out.println("Monthly payments and fees");
		System.out.println("Checking fee: " + chk.getMonthlyFeeFormated());
		System.out.println("Savings interest payment: " + svg.getMonthlyInterestPaymentFormated() + "\n");
		
		chk.doMonthlyFee();
		svg.doMonthlyInterestPayment();
		
		System.out.println("Final balances");
		System.out.println("Checking: " + chk.getBalanceFormated());
		System.out.println("Svaings: " + svg.getBalanceFormated());
		
		System.out.println("Bye");
	}
}
