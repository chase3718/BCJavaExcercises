package ui;

import business.Stuffy;
import util.*;

public class StuffyDispenserApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Stuffy Dispenser App\n");
		System.out.println("This app will mimic the functionality of a stuffy dispensing machine."
				+ "\nThe user will be able to select a stuff, and the app should return one");
		
		Console in = new Console();
		Stuffy[] stuffies = new Stuffy[10];

		// Initialize a list of Stuffies
		stuffies[0] = new Stuffy(1, "Dog", "Red", "Large", 4);
		stuffies[1] = new Stuffy(2, "Cat", "Green", "Small", 4);
		stuffies[2] = new Stuffy(3, "Dragon", "Purple", "Medium", 6);
		stuffies[3] = new Stuffy(4, "Snail", "Yellow", "X-Large", 0);
		stuffies[4] = new Stuffy(5, "Platypus", "Blue", "Medium", 4);
		stuffies[5] = new Stuffy(6, "Octupus", "Purple", "Large", 8);
		stuffies[6] = new Stuffy(7, "Squirrel", "Brown", "Small", 4);
		stuffies[7] = new Stuffy(8, "StarFish", "Pink", "X-Large", 5);
		stuffies[8] = new Stuffy(9, "Lobster", "Red", "Large", 10);
		stuffies[9] = new Stuffy(10, "Spider", "Clear", "Small", 8);
		
		for (int i = 0; i < stuffies.length; i ++) {
			System.out.println(stuffies[i]);
		}
		
		// Loop until user wants to quit
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {

			// Inside loop
			// 1) Prompt for user input ("Pick a stuffy")
			int selectionNbr = in.getInt("Pick a stuffy by number (1-10): ", 0, 11);
			// 2) Do business logic (retrieve a stuffy from the list)
			Stuffy selectedStuffy = stuffies[selectionNbr-1];
			// 3) Display the selected stuffy
			System.out.println("Congratulations, you have a " + selectedStuffy.getSize() + ", " + selectedStuffy.getColor() + " " + selectedStuffy.getType() 
		                   	 + " stuffy!"); 
			// 4) Prompt to continue
			choice = in.getString("Continue? (y/n) ", true, "y", "n");
		}
		System.out.println("Bye");
	}
}
