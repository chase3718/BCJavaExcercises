package williams.presentation;
import java.util.Scanner;

public class Console {

	private static Scanner sc = new Scanner(System.in);

	public String getString(String prompt) {
		System.out.print(prompt);
		String s = sc.next();
		sc.nextLine();
		return s;
	}

	public int getInt(String prompt) {
		int i = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextInt()) {
				i = sc.nextInt();
				isValid = true;
			} else {
				System.out.println("Error! Invalid integer. Try again.");
			}
			sc.nextLine();
		}
		return i;
	}

	public int getInt(String prompt, int min, int max) {
		int i = 0;
		boolean isValid = false;
		while (!isValid) {
			i = getInt(prompt);
			if (i <= min) {
				System.out.println("Error! Number must be greater than " + min + ".");
			} else if (i >= max) {
				System.out.println("Error! Number muse be less than " + max + ".");
			} else {
				isValid = true;
			}
		}
		return i;
	}

	public double getDouble(String prompt) {
		double d = 0;
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			if (sc.hasNextDouble()) {
				d = sc.nextDouble();
				isValid = true;
			} else {
				System.out.println("Error! Invalid number. Try again.");
			}
			sc.nextLine();
		}
		return d;
	}

	public double getDouble(String prompt, double min, double max) {
		double d = 0;
		boolean isValid = false;
		while (!isValid) {
			d = getDouble(prompt);
			if (d <= min) {
				System.out.println("Error! Number must be greater than " + min + ".");
			} else if (d >= max) {
				System.out.println("Error! Number muse be less than " + max + ".");
			} else {
				isValid = true;
			}
		}
		return d;
	}

	public String getStringOptions(String prompt, String option1, String option2, boolean ignoreCase) {
		String s = "";
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			s = sc.nextLine();
			if (s.length() < 1) {
				System.out.println("Error! This entry is required. Try again.");
			} else {
				if (ignoreCase) {
					if (!s.equalsIgnoreCase(option1) && !s.equalsIgnoreCase(option2)) {
						System.out.println("Error! Must either be " + option1 + " or " + option2 + ".");
					} else {
						isValid = true;
					}
				} else {
					if (!s.equals(option1) && !s.equals(option2)) {
						System.out.println("Error! Must either be " + option1 + " or " + option2 + ". (Case Sensitive)");
					} else {
						isValid = true;
					}
				}
			}
			//sc.nextLine();
		}
		return s;
	}
}
