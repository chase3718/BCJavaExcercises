package ui;

import java.util.List;

import business.Stuffy;
import db.DAO;
import db.StuffyDB;
import util.*;

public class StuffyDispenserApp {

	private static DAO<Stuffy> stuffyDB = new StuffyDB();

	public static void main(String[] args) {
		System.out.println("Welcome to the Stuffy Dispenser");

		displayMenu();
		String action = "";
		while (!action.equalsIgnoreCase("exit")) {
			action = Console.getString("Enter a command:  ", true, "list", "add", "del", "help", "exit", "sel");

			if (action.equalsIgnoreCase("list")) {
				displayAllStuffies();
			} else if (action.equalsIgnoreCase("add")) {
				addStuffy();
			} else if (action.equalsIgnoreCase("del")) {
				deleteStuffy();
			} else if (action.equalsIgnoreCase("help")) {
				displayMenu();
			} else if (action.equalsIgnoreCase("sel")) {
				selectStuffy();
			}
		}

		System.out.println("Bye");
	}

	public static void displayMenu() {
		System.out.println("Command Menu");
		System.out.println("===============================");
		System.out.println("list	- List all stuffies");
		System.out.println("sel     - Select a stuffy");
		System.out.println("add     - Add a stuffy");
		System.out.println("del     - Delete a stuffy");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application");
	}

	private static void displayAllStuffies() {
		System.out.println("Stuffy List");
		System.out.println("==========================================");
		List<Stuffy> stuffies = stuffyDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (Stuffy p : stuffies) {
			sb.append(StringUtils.padWithSpaces(p.getId(), 4));
			sb.append(StringUtils.padWithSpaces(p.getType(), 10));
			sb.append(StringUtils.padWithSpaces(p.getColor(), 10));
			sb.append(StringUtils.padWithSpaces(p.getSize(), 10));
			sb.append(p.getLimbs());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void selectStuffy() {
		int id = Console.getInt("Select a stuffy by ID: ");
		Stuffy s = stuffyDB.get(id);
		
		if (s != null) {
			System.out.println("You chose a " + s.getSize() + ", " + s.getColor() + " " + s.getType() + " with " + s.getLimbs() + " limbs.");
		} else {
			System.out.println("That stuffy does not exist");
		}
		
	}

	private static void addStuffy() {
		String type = Console.getString("Enter stuffy type: ", true);
		String color = Console.getString("Enter stuffy color: ", true);
		String size = Console.getString("Enter stuffy size: ", true, "x-small", "small", "medium", "large", "x-large",
				"xs", "s", "m", "l", "xl");
		int limbs = Console.getInt("Enter number of limbs: ", -1, Integer.MAX_VALUE);

		Stuffy stuffy = new Stuffy(type, color, size, limbs);

		stuffyDB.add(stuffy);

		System.out.println("Stuffy has been added.\n");
	}

	private static void deleteStuffy() {
		int code = Console.getInt("Enter stuffy ID to delete: ");

		Stuffy p = stuffyDB.get(code);
		if (p != null) {
			stuffyDB.delete(p);
			System.out.println("Stuffy has been deleted.\n");
		} else {
			System.out.println("No stuffy matches that ID.\n");
		}
	}
}
