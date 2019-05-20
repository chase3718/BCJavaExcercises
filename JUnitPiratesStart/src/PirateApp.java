import java.util.ArrayList;

public class PirateApp {

	private static ArrayList<Pirate> pirates = new ArrayList<>();

	public static void main(String[] args) {

		Pirate p1 = addPirate("Jack Black");

		Pirate p2 = addPirate("Blackbeard");

		Pirate p3 = addPirate("Stubby", 0);

		System.out.println();

		printPirateStats();

		System.out.println("Pirate fight!!!");
		// p1 loses a limb
		p1.loseALimb();
		System.out.println();

		// p2 loses limbs
		for (int i = 0; i < 5; i++)
			p2.loseALimb();
		System.out.println();

		// p3 loses a limb
		p3.loseALimb();
		System.out.println();

		System.out.println("Tough fight... who's left?");
		for (Pirate p : pirates) {
			System.out.println("Is " + p.getName() + " dead? " + p.isDead());
		}
		System.out.println();

		printPirateStats();

		System.out.println("Bye!");
	}

	private static Pirate addPirate(String pirateName) {
		return addPirate(pirateName, 4);
	}

	private static Pirate addPirate(String pirateName, int limbs) {
		Pirate p = new Pirate(pirateName, limbs);
		pirates.add(p);
		System.out.println(p.getName() + " added. # of pirates = " + Pirate.getNumPirates());
		return p;
	}

	private static void printPirateStats() {
		System.out.println("# of Pirates = " + Pirate.getNumPirates());
		System.out.println("# of limbs lost = " + Pirate.getTotalLimbsLost());
		System.out.println("Pirates Summary:");
		System.out.println("================");
		for (Pirate p : pirates) {
			System.out.println(p);
		}
		System.out.println();
	}
}
