package business;

public enum Roshambo {
	ROCK, 
	PAPER, 
	SCISSORS;

	@Override
	public String toString() {
		String s = "";
		if (this.ordinal() == 0) {
			s = "Rock";
		} else if (this.ordinal() == 1) {
			s = "Paper";
		} else if (this.ordinal() == 2) {
			s = "Scissors";
		}
		return s;
	}

	public static String getWinner(String p, String c, Roshambo player, Roshambo comp) {
		if (player.ordinal() < comp.ordinal()) {
			if (player.ordinal() == 0 && comp.ordinal() == 2) {
				return p + " wins!";
			} else {
				return c + " wins!";
			}
		} else if (player.ordinal() > comp.ordinal()) {
			if (player.ordinal() == 2 && comp.ordinal() == 0) {
				return c + " wins!";
			} else {
				return p + " wins!";
			}
		} else {
			return "Draw";
		}
	}
}
