package ui;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import business.Product;
import business.PurchaseRequest;
import business.PurchaseRequestLineItem;
import business.User;
import business.Vendor;
import db.DAO;
import db.ProductDB;
import db.PurchaseRequestDB;
import db.PurchaseRequestLineItemDB;
import db.UserDB;
import db.VendorDB;
import util.Console;

public class prsApp {
	private static DAO<User> userDB = new UserDB();
	private static DAO<Vendor> vendorDB = new VendorDB();
	private static DAO<Product> productDB = new ProductDB();
	private static DAO<PurchaseRequest> purchaseRequestDB = new PurchaseRequestDB();
	private static DAO<PurchaseRequestLineItem> purchaseRequestLineItemDB = new PurchaseRequestLineItemDB();

	public static void main(String[] args) {
		System.out.println("Welcome to the Purchase Request System\n");

		displayMenu();

		String choice = Console.getString("Enter a command: ", true, "list", "sel", "add", "del", "help", "exit");

		while (!choice.equalsIgnoreCase("exit")) {
			if (choice.equalsIgnoreCase("list")) {
				displayListMenu();
				String listChoice = Console.getString("Enter a table to display: ", true, "users", "vendors",
						"products", "purReq", "lineitem");
				if (listChoice.equalsIgnoreCase("users")) {
					displayUsers();
				} else if (listChoice.equalsIgnoreCase("vendors")) {
					String choiceVendors = Console.getString("Display with products?(y/n): ", true, "y", "n");
					displayVendors(choiceVendors);
				} else if (listChoice.equalsIgnoreCase("products")) {
					displayProducts();
				} else if (listChoice.equalsIgnoreCase("purReq")) {
					displayPurchaseRequests();
				}
			} else if (choice.equalsIgnoreCase("sel")) {
				
			} else if (choice.equalsIgnoreCase("add")) {
				displayAddMenu();
				String addChoice = Console.getString("Enter a table to add to: ", true, "users", "vendors",
						"products", "purReq", "lineitem");
				addToTable(addChoice);
			}
			
			choice = Console.getString("Enter a command: ", true, "list", "sel", "add", "del", "help", "exit");

		}
		System.out.println("Bye");
	}
	
	private static void addToTable(String table) {
		if (table.equalsIgnoreCase("users")) {
			String userName = Console.getString    ("Username:     ",true);
			String password = Console.getString    ("Password:     ",true);
			String firstName = Console.getString   ("First Name:   ",true);
			String lastName = Console.getString    ("Last Name:    ",true);
			String phoneNumber = Console.getString ("Phone Number: ",true);
			String email = Console.getString       ("eMail:        ",true);
			boolean isReviewer = Console.getBoolean("Is Reviewer:  ");
			boolean isAdmin = Console.getBoolean   ("Is Admin:     ");
			User p = new User(userName, password, firstName, lastName, phoneNumber, email, isReviewer, isAdmin);
			userDB.add(p);
		}
		
	}
	
	private static void displayPurchaseRequests() {
		System.out.println("Purchase Requests");
		System.out.println("==========================================");
		List<PurchaseRequest> purchaseRequests = purchaseRequestDB.getAll();
		List<PurchaseRequestLineItem> purchaseRequestLineItems = purchaseRequestLineItemDB.getAll();
		//List<Product> products = productDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (PurchaseRequest p : purchaseRequests) {
			User u = userDB.get(p.getUserID());
			sb.append("=User: " + u.getUserName() + "=\n");
			sb.append("\tDescription:   " + p.getDescription() + "\n");
			sb.append("\tJustification: " + p.getJustification() + "\n");
			DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
			sb.append("\tDate Needed:   " + dtf.format(p.getDateNeeded()) + "\n");
			sb.append("\tDelivery Mode: " + p.getDeliveryMode() + "\n");
			sb.append("\tStatus:        " + p.getStatus() + "\n");
			sb.append("\t=Products=\n");
			for (PurchaseRequestLineItem d: purchaseRequestLineItems) {
				if (d.getPurchaseRequestID() == p.getID()) {
					Product n = productDB.get(d.getProductID());
					sb.append("\t\tPart Name:   " + n.getName() + "\n");
					sb.append("\t\tPart Number: " + n.getPartNumber() + "\n");
					sb.append("\t\tUnit:        " + n.getUnit() + "\n");
					sb.append("\t\tPrice:       " + n.getPriceFormatted() + "\n");
					Vendor v = vendorDB.get(n.getVendorID());
					sb.append("\t\tVendor:      " + v.getName() + "\n");
					double pr = n.getPrice() * d.getQuantity();
					sb.append("\tTotal:         " + pr);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void displayUsers() {
		System.out.println("Users");
		System.out.println("==========================================");
		List<User> users = userDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (User p : users) {
			sb.append("=Name: " + p.getFirstName() + " " + p.getLastName() + "=\n");
			sb.append("\tUser ID:       " + p.getID() + "\n");
			sb.append("\tUsername:      " + p.getUserName() + "\n");
			sb.append("\tPassword:      " + p.getPasswordStars() + "\n");
			sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
			sb.append("\tEmail:         " + p.getEmail() + "\n");
			sb.append("\tIs a Reviewer: " + p.isReviewer() + "\n");
			sb.append("\tIs an Admin:   " + p.isAdmin() + "\n");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void displayVendors(String c) {
		System.out.println("Vendors");
		System.out.println("==========================================");
		List<Vendor> vendors = vendorDB.getAll();
		StringBuilder sb = new StringBuilder();
		if (c.equalsIgnoreCase("n")) {

			for (Vendor p : vendors) {
				sb.append("=Name: " + p.getName() + "=\n");
				sb.append("\tVendor Code:   " + p.getCode() + "\n");
				sb.append("\tAddress:       " + p.getAddress() + ", " + p.getCity() + " " + p.getState() + " "
						+ p.getZip() + "\n");
				sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
				sb.append("\tEmail:         " + p.getEmail() + "\n");
				sb.append("\tPreApproved:   " + p.isPreApproved() + "\n");
				sb.append("\n");
			}
		} else {
			List<Product> products = productDB.getAll();
			for (Vendor p : vendors) {
				sb.append("=Name: " + p.getName() + "=\n");
				sb.append("\tVendor Code:   " + p.getCode() + "\n");
				sb.append("\tAddress:       " + p.getAddress() + ", " + p.getCity() + " " + p.getState() + " "
						+ p.getZip() + "\n");
				sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
				sb.append("\tEmail:         " + p.getEmail() + "\n");
				sb.append("\tPreApproved:   " + p.isPreApproved() + "\n");
				sb.append("\t=Products=\n");
				for (Product n : products) {
					if (n.getVendorID() == p.getID()) {
						sb.append("\t\tPart Name:   " + n.getName() + "\n");
						sb.append("\t\tPart Number: " + n.getPartNumber() + "\n");
						sb.append("\t\tUnit:        " + n.getUnit() + "\n");
						sb.append("\t\tPrice:       " + n.getPriceFormatted() + "\n\n");
					}
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void displayProducts() {
		System.out.println("Products");
		System.out.println("==========================================");
		List<Product> products = productDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (Product p : products) {
			sb.append("\tPart Name:   " + p.getName() + "\n");
			sb.append("\tPart Number: " + p.getPartNumber() + "\n");
			sb.append("\tUnit:        " + p.getUnit() + "\n");
			sb.append("\tPrice:       " + p.getPriceFormatted() + "\n");
			Vendor v = vendorDB.get(p.getVendorID());
			sb.append("\tVendor:      " + v.getName() + "\n");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void displayListMenu() {
		System.out.println("List of Tables");
		System.out.println("users   - Display all users");
		System.out.println("vendors - Display all vendors");
		System.out.println("products- Display all products");
		System.out.println("purReq  - Display all purchase requests");
		System.out.println("lineitem- Display all line items");
	}
		
	private static void displayAddMenu() {
		System.out.println("List of Tables");
		System.out.println("users   - Add to users");
		System.out.println("vendors - Add to vendors");
		System.out.println("products- Add to products");
		System.out.println("purReq  - Add to purchase requests");
		System.out.println("lineitem- Add to line items");
	}
	
	private static void displayMenu() {
		System.out.println("Command Menu");
		System.out.println("===============================");
		System.out.println("list	- List a table of your choice");
		System.out.println("sel     - Select from a table");
		System.out.println("add     - Add to a table");
		System.out.println("del     - Delete from a table");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application");
	}
}
