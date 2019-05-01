package williams.account;
import java.math.BigDecimal;
import java.text.NumberFormat;

import williams.interfaces.Balanceable;
import williams.interfaces.Depositable;
import williams.interfaces.Withdrawable;

public class Account implements Depositable, Withdrawable, Balanceable {

	protected double balance;

	public Account(double balance) {
		this.balance = balance;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}
	
	public String getBalanceFormated() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		BigDecimal MIR = new BigDecimal(balance);
		
		return currency.format(MIR);
	}
	
	@Override
	public void setBalance(double amount) {
		// TODO Auto-generated method stub
		this.balance = amount;
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		if (amount <= balance) {
			balance -= amount;
		} else {
			System.out.println("Error! Cannot withdraw more than account balance");
		}
	}

	@Override
	public void deposit(double amount) {
		// TODO Auto-generated method stub
		balance += amount;
	}
}
