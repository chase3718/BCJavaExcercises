import java.util.Scanner;

public class Console {

	private Scanner sc = new Scanner(System.in);

	public String getString(String prompt) {
		System.out.print(prompt);
		String str = sc.nextLine();
		return str;
	}

	public String getString(String prompt, boolean doReq) {
		boolean isValid = false;
		String str = "";
		boolean hasData = false;
		while (!isValid) {
			System.out.print(prompt);
			str = sc.nextLine();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) != 32) {
					hasData = true;
					break;
				}
			}
			if (doReq && !hasData) {
				System.out.println("Error. You must enter data.");
			} else {
				isValid = true;
			}
		}
		return str;
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

	public String getString(String prompt, boolean ignoreCase, String... options) {
		String s = "";
		String optArr = "";
		for (int i = 0; i < options.length; i++) {
			optArr += " " + options[i];
		}
		boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			s = sc.nextLine();
			if (s.length() < 1) {
				System.out.println("Error! This entry is required. Try again.");
			} else {
				for (int i = 0; i < options.length; i++) {
					if (ignoreCase) {
						if (s.equalsIgnoreCase(options[i])) {
							isValid = true;
							break;
						}
					} else {
						if (s.equals(options[i])) {
							isValid = true;
							break;
						}
					}
				}
				if (!isValid) {
					if (ignoreCase) {
						System.out.println("Error! Options are:" + optArr + ". Try Again");
					} else {
						System.out.println("Error! Options are:" + optArr + ". Try Again (Case Sensitive)");
					}
				}
			}
		}
		if (ignoreCase) {
			return s.toUpperCase();
		} else {
			return s;
		}
	}
}
