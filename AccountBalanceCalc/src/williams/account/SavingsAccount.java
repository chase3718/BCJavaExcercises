package williams.account;
import java.text.NumberFormat;
import java.math.*;

public class SavingsAccount extends Account{
	private double monthlyInterestRate;
	private double monthlyInterestPayment;
	
	public SavingsAccount(double amount) {
		super(amount);
		this.monthlyInterestRate = 1;
		this.monthlyInterestPayment = amount * (monthlyInterestRate/100);
	}
	
	public void setMonthlyInterestPayment() {
		monthlyInterestPayment = this.getBalance() * (monthlyInterestRate/100);
	}
	
	public double getMonthlyInterestPayment() {
		return monthlyInterestPayment;
	}
	
	public void doMonthlyInterestPayment() {
		this.balance += monthlyInterestPayment;
	}
	
	public String getMonthlyInterestPaymentFormated() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		BigDecimal MIR = new BigDecimal(monthlyInterestPayment);
		
		return currency.format(MIR);
	}
}
