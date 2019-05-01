
public class PersonManagerApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the Person Manager App\n");
		
		MyConsole input = new MyConsole();
		String run = "y";
		
		while (run.equalsIgnoreCase("y")) {
			String type = "";
			boolean isValid = false;
			while (!isValid) {
				type = input.getString("\nCreate Customer or Employee? (c/e) ");
				if (type.equalsIgnoreCase("c") || type.equalsIgnoreCase("e")) {
					isValid = true;
				} else {
					System.out.println("Error! Entry must be c or e. Try again.");
				}
				System.out.println();
			}
			isValid = false;
			
			if (type.equalsIgnoreCase("c")) {
				Customer c = new Customer();
				c.setFirstName(input.getString("\nFirst Name: "));
				c.setLastName(input.getString("\nLast Name: "));
				c.setCustomerNumber(input.getString("\nCustomer Number: "));
				
				System.out.println("\nYou entered a new customer\n"); 
				System.out.print(c.toString());
			} else {
				Employee e = new Employee();
				e.setFirstName(input.getString("\nFirst Name: "));
				e.setLastName(input.getString("\nLast Name: "));
				e.setSsn(input.getString("\nSSN: "));
				
				System.out.println("\nYou entered a new employee.\n");
				System.out.println(e.toString());
			}
			System.out.println();
			
			run = input.getString("\nContinue? (y/n) ");
			
		}
		System.out.println("\nBye");
	}
}
