package business;

public class Lisa extends Player {
	public Lisa() {
		super("Lisa", generateRoshambo(getRandomChoice()));
	}

	private static String getRandomChoice() {
		int l = (int) (Math.random() * 3);
		String c = "";
		if (l == 0) {
			c = "r";
		} else if (l == 1) {
			c = "p";
		} else {
			c = "s";
		}
		return c;
	}
}
