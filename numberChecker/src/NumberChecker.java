
public class NumberChecker {
	public static void main(String[] args) {
		System.out.println("Welcome to the Odd/Even Checker\n");
		
		Console input = new MyConsole();
		String check = "y";
		
		while (check.equals("y")) {
			int n = input.getInt("Enter an integer: ");
			
			System.out.println();
			
			if (n % 2 == 0) {
				System.out.println("The number " + n + " is even.\n");
			} else {
				System.out.println("The number " + n + " is odd.\n");
			}
			
			check = input.getString("Continue? (y/n) ");
			System.out.println();
		}
		
		System.out.println("Bye");
	}
}
