package com.stuffy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import com.stuffy.business.Stuffy;
import com.stuffy.db.StuffyDB;
import com.stuffy.util.*;

@SpringBootApplication
public class StuffyJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StuffyJpaDemoApplication.class, args);
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
		List<Stuffy> stuffies = StuffyDB.getAll();
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
		Stuffy s = StuffyDB.get(id);
		
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
		int limbs = Console.getInt("Enter number of limbs: ", -1, 100);

		Stuffy stuffy = new Stuffy(type, color, size, limbs);

		StuffyDB.insert(stuffy);
		
		System.out.println("Stuffy has been added.\n");
	}

	private static void deleteStuffy() {
		int code = Console.getInt("Enter stuffy ID to delete: ");

		Stuffy p = StuffyDB.get(code);
		if (p != null) {
			StuffyDB.delete(p);
			System.out.println("Stuffy has been deleted.\n");
		} else {
			System.out.println("No stuffy matches that ID.\n");
		}
	}
}
