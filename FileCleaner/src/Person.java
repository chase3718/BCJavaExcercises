
public class Person {
	private String fName;
	private String lName;
	private String email;
	
	public Person (String fName, String lName, String email) {
		this.fName = fName.trim();
		this.lName = lName.trim();
		this.email = email.trim();
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName.trim();
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}
	
	public String toTitleCase(String str) {
		String s = str.substring(0,1).toUpperCase() + str.substring(1,str.length()).toLowerCase();
		return s;
	}
	
	@Override
	public String toString() {
		return toTitleCase(fName) + "," + toTitleCase(lName) + "," + email.toLowerCase();
	}
	
	
}
