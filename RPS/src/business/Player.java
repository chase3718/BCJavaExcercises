package business;

public abstract class Player {

	private String name;
	private Roshambo player;

	public Player() {
		this.name = "";
		this.player = null;
	}

	public Player(String name) {
		this.name = name;
	}

	public Player(String name, Roshambo c) {
		this.name = name;
		this.player = c;
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

	public Roshambo getPlayer() {
		return player;
	}

	public String getPlayerString() {
		return player.toString();
	}

	public void setPlayer(Roshambo player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return getNameUpper() + ": " + getPlayerString();
	}

}
