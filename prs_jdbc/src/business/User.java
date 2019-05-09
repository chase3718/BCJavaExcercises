package business;

public class User {
	private int ID;
	private String userName;
	private String Password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private boolean isReviewer;
	private boolean isAdmin;

	public User() {

	}

	public User(String userName, String password, String firstName, String lastName, String phoneNumber, String email,
			boolean isReviewer, boolean isAdmin) {
		super();
		this.userName = userName;
		Password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
	}

	public User(int id, String userName, String password, String firstName, String lastName, String phoneNumber,
			String email, boolean isReviewer, boolean isAdmin) {
		super();
		this.ID = id;
		this.userName = userName;
		Password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isReviewer = isReviewer;
		this.isAdmin = isAdmin;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}
	
	public String getPasswordStars() {
		String s = "";
		for (int i = 0; i < Password.length(); i++) {
			s += "*";
		}
		return s;
	}
	
	public void setPassword(String password) {
		Password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isReviewer() {
		return isReviewer;
	}

	public void setReviewer(boolean isReviewer) {
		this.isReviewer = isReviewer;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + ID + ", userName=" + userName + ", Password=" + Password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", isReviewer="
				+ isReviewer + ", isAdmin=" + isAdmin + "]";
	}

}
