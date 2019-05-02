import java.util.ArrayList;

public class PrimeNumberCheckerApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Prime Number Checker App\n");
		
		Console in = new Console();
		
		do {
		
		int num = in.getInt("Enter an integer between 1 and 5000: ", 0, 5001);
		
		ArrayList<Integer> factors = getFactors(num);
		
		if (factors.size() == 1) {
			System.out.println(num + " is a prime number.");
		} else {
			System.out.println(num + " is not a prime number.");
			String out = "It has " + factors.size() + " factors: ";
			for (int i = 0; i < factors.size(); i ++) {
				if (i == factors.size() - 1) {
					out += "and ";
				}
				out += factors.get(i);
				if (i < factors.size() - 1) {
					out += ", ";
				}
			}
			out += ".";
			System.out.println(out);
		}
		
		} while (in.getString("Continue? (y/n) ", true, "y", "n").equalsIgnoreCase("y"));
		
		System.out.println("Bye");
	}
	
	public static ArrayList<Integer> getFactors(int n) {
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for (int i = 1; i <= (int)(n/2); i ++) {
			if (n % i == 0) {
				factors.add(i);
			}
		}
		
		
		return factors;
	}
	
	
}
