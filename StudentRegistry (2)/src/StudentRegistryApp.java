import java.util.Scanner;

public class StudentRegistryApp {
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		String fName = "";
		String lName = "";
		int dob;
		
		System.out.println("Hello, and welcome to the student registry");
		System.out.print("Please enter your first name: ");
		
		fName = sc.next();
		
		System.out.print("Please enter your last name: ");
		
		lName = sc.next();
		
		System.out.print("Please enter your birth year: ");
		
		dob = sc.nextInt();
		
		System.out.println("Welcome " + fName + " " + lName);
		System.out.println("Registration Complete");
		System.out.println("Your password is: " + fName + "*" + dob);
		System.out.println("Thank you");
		sc.close();
	}
}
