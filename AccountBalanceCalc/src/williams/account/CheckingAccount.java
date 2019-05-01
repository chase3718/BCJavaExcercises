package williams.account;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class CheckingAccount extends Account {

	private double monthlyFee;

	public CheckingAccount(double amount) {
		super(amount);
		this.monthlyFee = 1.00;
	}

	public void setMonthlyFee(double amount) {
		this.monthlyFee = amount;
	}
	
	public void doMonthlyFee() {
		this.balance -= monthlyFee;
	}
	
	public double getMonthlyFee() {
		return monthlyFee;
	}
	
	public String getMonthlyFeeFormated() {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		BigDecimal MIR = new BigDecimal(monthlyFee);
		
		return currency.format(MIR);
	}
}
