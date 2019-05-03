package business;

public abstract class Player {

	private String name;
	private Roshambo hand;

	public Player() {
		this.name = "";
		this.hand = null;
	}

	public Player(String name) {
		this.name = name;
		this.hand = null;
	}

	public Player(String name, Roshambo c) {
		this.name = name;
		this.hand = c;
	}

	public static Roshambo generateRoshambo(String s) {
		Roshambo p;
		if (s.equalsIgnoreCase("r")) {
			p = Roshambo.ROCK;
		} else if (s.equalsIgnoreCase("p")) {
			p = Roshambo.PAPER;
		} else {
			p = Roshambo.SCISSORS;
		}

		return p;
	}

	public String getName() {
		return name;
	}

	public String getNameUpper() {
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public Roshambo getHand() {
		return hand;
	}

	public String getHandString() {
		return hand.toString();
	}

	public void setHand(Roshambo player) {
		this.hand = player;
	}

	@Override
	public String toString() {
		return getNameUpper() + ": " + getHandString();
	}

}
