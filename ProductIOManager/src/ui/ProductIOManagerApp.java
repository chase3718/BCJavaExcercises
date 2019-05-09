package ui;

import java.util.*;

import business.Product;
import db.DAO;
import db.ProductDB;
//import db.ProductTextFile;
import util.Console;
import util.StringUtils;

public class ProductIOManagerApp {

	private static DAO<Product> productDB = new ProductDB();

	public static void main(String[] args) {
		System.out.println("Welcome to the Product Manager - Text File Edition");

		displayMenu();
		String action = "";
		while (!action.equalsIgnoreCase("exit")) {
			action = Console.getString("Enter a command:  ", true, "list", "add", "del", "help", "exit");

			if (action.equalsIgnoreCase("list")) {
				displayAllProducts();
			} else if (action.equalsIgnoreCase("add")) {
				addProduct();
			} else if (action.equalsIgnoreCase("del")) {
				deleteProduct();
			} else if (action.equalsIgnoreCase("help")) {
				displayMenu();
			}
		}

		System.out.println("Bye");
	}

	public static void displayMenu() {
		System.out.println("Command Menu");
		System.out.println("===============================");
		System.out.println("list	- List all products");
		System.out.println("add     - Add a product");
		System.out.println("del     - Delete a product");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application");
	}

	private static void displayAllProducts() {
		System.out.println("Product List");
		System.out.println("======================================================");
		List<Product> products = productDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (Product p : products) {
			sb.append(StringUtils.padWithSpaces(p.getCode(), 8));
			sb.append(StringUtils.padWithSpaces(p.getDescription(), 40));
			sb.append(p.getPriceFormatted());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void addProduct() {
		String code = Console.getString("Enter product code: ", true);
		String desc = Console.getString("Enter product description: ", true);
		double price = Console.getDouble("Enter price: ", 0, Double.POSITIVE_INFINITY);

		Product product = new Product(code, desc, price);

		productDB.add(product);

		System.out.println(desc + " has been added.\n");
	}

	private static void deleteProduct() {
		String code = Console.getString("Enter product code to delete: ", true);

		Product p = productDB.get(code);
		if (p != null) {
			productDB.delete(p);
			System.out.println(p.getDescription() + " has been deleted.\n");
		} else {
			System.out.println("No product matches that code.\n");
		}
	}
}
