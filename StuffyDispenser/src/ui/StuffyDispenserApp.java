package ui;

import java.util.ArrayList;
import java.util.List;

import business.Stuffy;
import util.*;

public class StuffyDispenserApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Stuffy Dispenser App\n");
		System.out.println("This app will mimic the functionality of a stuffy dispensing machine."
				+ "\nThe user will be able to select a stuff, and the app should return one");

		Console in = new Console();
		List<Stuffy> stuffies = new ArrayList<Stuffy>();

		stuffies.add(new Stuffy(1, "Dog", "Red", "Large", 4));
		stuffies.add(new Stuffy(2, "Cat", "Green", "Small", 4));
		stuffies.add(new Stuffy(3, "Dragon", "Purple", "Medium", 6));
		stuffies.add(new Stuffy(4, "Snail", "Yellow", "X-Large", 0));
		stuffies.add(new Stuffy(5, "Platypus", "Blue", "Medium", 4));
		stuffies.add(new Stuffy(6, "Octupus", "Purple", "Large", 8));
		stuffies.add(new Stuffy(7, "Squirrel", "Brown", "Small", 4));
		stuffies.add(new Stuffy(8, "StarFish", "Pink", "X-Large", 5));
		stuffies.add(new Stuffy(9, "Lobster", "Red", "Large", 10));
		stuffies.add(new Stuffy(10, "Spider", "Clear", "Small", 8));

		/*
		 * for (Stuffy s : stuffies) { System.out.println(s); }
		 */

		/*
		 * Stuffy[] stuffies = new Stuffy[10];
		 * 
		 * // Initialize a list of Stuffies stuffies[0] = new Stuffy(1, "Dog", "Red",
		 * "Large", 4); stuffies[1] = new Stuffy(2, "Cat", "Green", "Small", 4);
		 * stuffies[2] = new Stuffy(3, "Dragon", "Purple", "Medium", 6); stuffies[3] =
		 * new Stuffy(4, "Snail", "Yellow", "X-Large", 0); stuffies[4] = new Stuffy(5,
		 * "Platypus", "Blue", "Medium", 4); stuffies[5] = new Stuffy(6, "Octupus",
		 * "Purple", "Large", 8); stuffies[6] = new Stuffy(7, "Squirrel", "Brown",
		 * "Small", 4); stuffies[7] = new Stuffy(8, "StarFish", "Pink", "X-Large", 5);
		 * stuffies[8] = new Stuffy(9, "Lobster", "Red", "Large", 10); stuffies[9] = new
		 * Stuffy(10, "Spider", "Clear", "Small", 8);
		 * 
		 * for (int i = 0; i < stuffies.length; i ++) { System.out.println(stuffies[i]);
		 * }
		 * 
		 */
		// Loop until user wants to quit

		String choice = in.getString("Would you like to search, add stuffies, or exit? (search, add, exit) ", true,
				"search", "add", "exit");
		while (!choice.equalsIgnoreCase("exit")) {

			if (choice.equalsIgnoreCase("search")) {
				// Inside loop
				// 1) Prompt for user input ("Pick a stuffy")
				// int selectionNbr = in.getInt("Pick a stuffy by number (1-10): ", 0, 11);
				// 2) Do business logic (retrieve a stuffy from the list)
				// Stuffy selectedStuffy = stuffies[selectionNbr-1];
				// 3) Display the selected stuffy
				// System.out.println("Congratulations, you have a " + selectedStuffy.getSize()
				// + ", " + selectedStuffy.getColor() + " " + selectedStuffy.getType() + "
				// stuffy!");

				Stuffy selectedStuffy = null;

				int selectionNbr = in.getInt("Pick a stuffy by number (1-" + stuffies.size() + "): ", 0,
						stuffies.size() + 1);

				for (Stuffy s : stuffies) {
					if (s.getId() == selectionNbr) {
						selectedStuffy = s;
					}
				}

				System.out.println("Congratulations, you have a " + selectedStuffy.getSize() + ", "
						+ selectedStuffy.getColor() + " " + selectedStuffy.getType() + "stuffy!");

			} else {
				int id = in.getInt("Stuffy ID: ");
				String t = in.getString("Stuffy Type: ");
				String c = in.getString("Stuffy Color: ");
				String s = in.getString("Stuffy Size: ");
				int l = in.getInt("Stuffy # Limbs; ");
				Stuffy stuffy = new Stuffy(id, t, c, s, l);
				stuffies.add(stuffy);
			}

			// 4) Prompt to continue

			choice = in.getString("Search, add stuffies, or exit? (search, add, exit) ", true, "search", "add", "exit");
		}
		System.out.println("Bye");
	}
}
