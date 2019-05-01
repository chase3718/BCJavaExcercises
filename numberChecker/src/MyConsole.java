import java.util.Scanner;

public class MyConsole extends Console {
	
	private static Scanner sc = new Scanner(System.in);
	
	@Override
	public String getString(String prompt) {
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			s = sc.nextLine();
			if (s.length() < 1) {
				System.out.println("Error. This entry is required. Try again.");
			} else {
				isValid = true;
			}
			sc.nextLine();
		}
		return s;
	}

}
