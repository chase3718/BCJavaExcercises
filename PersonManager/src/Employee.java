
public class Employee extends Person {
	
	private String ssn;

	public Employee(String firstName, String lastName, String ssn) {
		super(firstName, lastName);
		this.ssn = ssn;
	}
	
	public Employee() {
		super();
		ssn = "";
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getMaskedSsn() {
		return "xxx-xx-" + ssn.substring(ssn.length()-4);
	}
	
	@Override
	public String toString() {
		return super.toString() + "\nSSN: " + getMaskedSsn();
	}
}
