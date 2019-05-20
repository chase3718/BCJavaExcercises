public class Pirate {

	protected String name;
	protected int limbsRemaining;
	protected static int numPirates = 0;
	protected static int totalLimbsLost = 0;

	// A typical pirate will have 4 limbs - 2 arms, 2 legs
	public Pirate(String name) {
		this.name = name;
		limbsRemaining = 4;
		numPirates++;
	}

	public Pirate(String name, int limbs) {
		this.name = name;
		limbsRemaining = limbs;
		numPirates++;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public int getLimbsRemaining() {
		return limbsRemaining;
	}

	public boolean isDead() {
		return (limbsRemaining < 0);
	}

	/*
	 * A pirate could lose a limb during combat. If so, we deduct a limb. If the
	 * pirate already lost all his/her limbs then the pirate dies and cannot lose
	 * anymore limbs.
	 */
	public void loseALimb() {
		if (limbsRemaining < 0) {
			System.out.println(name + " is dead. No more limbs!");
		} else if (limbsRemaining == 0) {
			limbsRemaining--;
			totalLimbsLost++;
			System.out.println("AAAARGH!!!!! " + name + " died!");
		} else {
			limbsRemaining--;
			totalLimbsLost++;
			System.out.println("AAAARGH! " + name + " says, \"I lost me limb!\"");
		}
	}

	public static int getNumPirates() {
		return numPirates;
	}

	public static int getTotalLimbsLost() {
		return totalLimbsLost;
	}

	@Override
	public String toString() {
		return "Pirate '" + name + "' has " + getLimbsRemaining() + " limbs remaining.";
	}

}
