package business;

import java.text.NumberFormat;

public class Calculation {
	
	private String calc;
	private String[] calcArr;

	public Calculation(String str) {
		calc = str;
	}

	public static String getStringCheckForErrors(String prompt) {
		boolean isValid = false;
		String str = prompt;
		while (!isValid) {
			boolean doEr = true;
			str = str.trim();
			int numOfSpaces = 0;
			String prev = "";
			str += " ";
			for (int i = 0; i < str.length() - 1; i++) {
				if (str.charAt(i) == 32) {
					if (prev.equals(" ")) {
						doEr = false;
						System.out.println("\nError. Use only one space to separate.");
						numOfSpaces = 0;
						break;
					}
					numOfSpaces++;

				}
				int a = str.charAt(i);
				if ((a == 37 || a == 42 || a == 43 || a == 45) && !prev.equals(" ") && str.charAt(i + 1) != 32) {
					System.out.println("\nError. Do not use unnecessary characters.");
					doEr = false;
					numOfSpaces = 0;
					break;
				}
				if (a != 32 && a != 37 && a != 42 && a != 43 && a != 45 && a != 46 && a != 47 && a < 48 || a > 57) {
					System.out.println("\nError. Alphabetic/Non-Numeric variables not allowed");
					doEr = false;
					numOfSpaces = 0;
					break;
				}
				prev = str.substring(i, i + 1);
			}
			if (numOfSpaces == 2) {
				isValid = true;
			} else if (numOfSpaces > 2 && doEr) {
				System.out.println("\nError. Only one operation available. Do not enter any unnecessary spaces.");
			} else if (doEr) {
				System.out.println("\nError. You must separate the function with spaces.");
			}
		}
		return str;
	}

	public String doCalculation() {
		double calc = 0;
		double[] nums;
		String[] opps;
		int numsl = 0;
		int oppsl = 0;
		for (int i = 0; i < this.calcArr.length; i++) {
			if (this.calcArr[i].charAt(0) >= 48 && this.calcArr[i].charAt(0) <= 57) {
				numsl++;
			} else if (this.calcArr[i].charAt(0) == 42 || this.calcArr[i].charAt(0) == 43
					|| this.calcArr[i].charAt(0) == 45 || this.calcArr[i].charAt(0) == 47
					|| this.calcArr[i].charAt(0) == 37) {
				oppsl++;
			}
		}
		nums = new double[numsl];
		opps = new String[oppsl];
		numsl = 0;
		oppsl = 0;
		for (int i = 0; i < this.calcArr.length; i++) {
			if (this.calcArr[i].charAt(0) >= 48 && this.calcArr[i].charAt(0) <= 57) {
				nums[numsl] = Double.parseDouble(this.calcArr[i]);
				numsl++;
			} else if (this.calcArr[i].charAt(0) == 42 || this.calcArr[i].charAt(0) == 43
					|| this.calcArr[i].charAt(0) == 45 || this.calcArr[i].charAt(0) == 47
					|| this.calcArr[i].charAt(0) == 37 || this.calcArr[i].charAt(0) == 120) {
				opps[oppsl] = this.calcArr[i];
			}
		}
		if (opps[0].equals("+")) {
			calc = nums[0] + nums[1];
		} else if (opps[0].equals("-")) {
			calc = nums[0] - nums[1];
		} else if (opps[0].equals("*") || opps[0].equals("x")) {
			calc = nums[0] * nums[1];
		} else if (opps[0].equals("/")) {
			calc = nums[0] / nums[1];
		} else if (opps[0].equals("%")) {
			calc = nums[0] % nums[1];
		}
		NumberFormat number = NumberFormat.getNumberInstance();
		return "\n" + number.format(nums[0]) + " " + opps[0] + " " + number.format(nums[1]) + " = "
				+ number.format(calc);
	}

	public void splitIntoParts() {
		/*
		 * int numOfSpaces = 1;
		for (int i = 0; i < this.calc.length() - 1; i++) {
			if (this.calc.substring(i, i + 1).equals(" "))
				numOfSpaces++;
		}
		if (numOfSpaces == 1) {
			System.out.println("\nError. Entered incorrectly.");
		}
		String[] split = new String[numOfSpaces];
		calcArr = new String[numOfSpaces];
		for (int i = 0; i < split.length; i++) {
			split[i] = "";
		}
		int index = 0;
		for (int i = 0; i < this.calc.length(); i++) {
			if (this.calc.substring(i, i + 1).equals(" ")) {
				index++;
			} else {
				split[index] += this.calc.substring(i, i + 1);
			}
		}
		calcArr = split; 
		*/
		calcArr = calc.split(" ");
	}

	public static String arrayToString(String[] str) {
		String r = "";
		for (int i = 0; i < str.length; i++) {
			r += str[i] + " ";
		}

		return r;
	}
}
