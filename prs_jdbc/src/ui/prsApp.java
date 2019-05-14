package ui;

import java.time.LocalDate;
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
import db.ProductTextFile;
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
				displayListMenu();
				String selChoice = Console.getString("Choose a table to select from: ", true, "users", "vendors",
						"products", "purReq", "lineitem");
				selectFromTable(selChoice);
			} else if (choice.equalsIgnoreCase("add")) {
				displayAddMenu();
				String addChoice = Console.getString("Enter a table to add to: ", true, "users", "vendors", "products",
						"purReq", "lineitem");
				if (addChoice.equalsIgnoreCase("products")) {
					String file = Console.getString("Would you like to add from file?(y/n): ", true, "y", "n", "yes",
							"no");
					if (file.equalsIgnoreCase("y") || file.equalsIgnoreCase("yes")) {
						displayVendors("n");
						int vId = Console.getInt("Choose a vendor by ID: ");
						ProductTextFile ptf = new ProductTextFile();
						addToTable(ptf.getAll(vId));
					} else {
						addToTable(addChoice);
					}
				} else {
					addToTable(addChoice);
				}
			} else if (choice.equalsIgnoreCase("del")) {
				String delChoice = Console.getString("Enter a table to delete from: ", true, "users", "vendors",
						"products", "purReq", "lineitem");
				delFromTable(delChoice);
			}

			choice = Console.getString("Enter a command: ", true, "list", "sel", "add", "del", "help", "exit");

		}
		System.out.println("Bye");
	}

	private static void selectFromTable(String table) {
		if (table.equalsIgnoreCase("users")) {
			User p = userDB.get(Console.getInt("Select a user by ID: ", 0, userDB.getAll().size() + 1));
			System.out.println("User");
			System.out.println("==========================================");
			StringBuilder sb = new StringBuilder();
			sb.append("=Name: " + p.getFirstName() + " " + p.getLastName() + "=\n");
			sb.append("\tUser ID:       " + p.getID() + "\n");
			sb.append("\tUsername:      " + p.getUserName() + "\n");
			sb.append("\tPassword:      " + p.getPasswordStars() + "\n");
			sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
			sb.append("\tEmail:         " + p.getEmail() + "\n");
			sb.append("\tIs a Reviewer: " + p.isReviewer() + "\n");
			sb.append("\tIs an Admin:   " + p.isAdmin() + "\n");
			sb.append("\n");
			System.out.println(sb.toString());
		} else if (table.equalsIgnoreCase("vendors")) {
			Vendor p = vendorDB.get(Console.getInt("Select a vendor by ID: ", 0, vendorDB.getAll().size() + 1));
			String c = Console.getString("Display with products(y/n): ", true, "y", "n");
			System.out.println("Vendors");
			System.out.println("==========================================");
			List<Vendor> vendors = vendorDB.getAll();
			StringBuilder sb = new StringBuilder();
			if (c.equalsIgnoreCase("n")) {

				sb.append("=Name: " + p.getName() + "=\n");
				sb.append("\tVendor Code:   " + p.getCode() + "\n");
				sb.append("\tAddress:       " + p.getAddress() + ", " + p.getCity() + " " + p.getState() + " "
						+ p.getZip() + "\n");
				sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
				sb.append("\tEmail:         " + p.getEmail() + "\n");
				sb.append("\tPreApproved:   " + p.isPreApproved() + "\n");
				sb.append("\n");

			} else {
				List<Product> products = productDB.getAll();

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
			System.out.println(sb.toString());
		} else if (table.equalsIgnoreCase("products")) {
			Product p = productDB.get(Console.getInt("Select a product by ID: ", 0, productDB.getAll().size() + 1));
			System.out.println("Products");
			System.out.println("==========================================");
			StringBuilder sb = new StringBuilder();

			sb.append("\tPart Name:   " + p.getName() + "\n");
			sb.append("\tPart Number: " + p.getPartNumber() + "\n");
			sb.append("\tUnit:        " + p.getUnit() + "\n");
			sb.append("\tPrice:       " + p.getPriceFormatted() + "\n");
			Vendor v = vendorDB.get(p.getVendorID());
			sb.append("\tVendor:      " + v.getName() + "\n");
			sb.append("\n");

			System.out.println(sb.toString());
		} else if (table.equalsIgnoreCase("purreq")) {
			
		}
	}

	private static void delFromTable(String table) {
		if (table.equalsIgnoreCase("users")) {
			userDB.delete(userDB.get(Console.getInt("Choose a user ID to delete: ")));
		} else if (table.equalsIgnoreCase("vendors")) {
			vendorDB.delete(vendorDB.get(Console.getInt("Choose a vendor ID to delete: ")));
		} else if (table.equalsIgnoreCase("products")) {
			productDB.delete(productDB.get(Console.getInt("Choose a product ID to delete: ")));
		} else if (table.equalsIgnoreCase("purreq")) {
			purchaseRequestDB.delete(purchaseRequestDB.get(Console.getInt("Choose a Purchase Request ID to delete: ")));
		} else {
			purchaseRequestLineItemDB.delete(
					purchaseRequestLineItemDB.get(Console.getInt("Choose a Purchase Request Line Item to delete: ")));
		}
	}

	private static void addToTable(List<Product> table) {
		for (Product p : table) {
			productDB.add(p);
		}
	}

	private static void addToTable(String table) {
		if (table.equalsIgnoreCase("users")) {
			String userName = Console.getString("Username:     ", true);
			String password = Console.getString("Password:     ", true);
			String firstName = Console.getString("First Name:   ", true);
			String lastName = Console.getString("Last Name:    ", true);
			String phoneNumber = Console.getString("Phone Number: ", true);
			String email = Console.getString("eMail:        ", true);
			boolean isReviewer = Console.getBoolean("Is Reviewer:  ");
			boolean isAdmin = Console.getBoolean("Is Admin:     ");
			User p = new User(userName, password, firstName, lastName, phoneNumber, email, isReviewer, isAdmin);
			userDB.add(p);
		} else if (table.equalsIgnoreCase("vendors")) {
			String vCode = Console.getString("Vendor Code: ", true);
			String name = Console.getString("Vendor Name: ", true);
			String address = Console.getString("Vendor Street Adress: ", true);
			String city = Console.getString("Vendor City: ", true);
			String state = Console.getString("Vendor State: ", true);
			String zip = Console.getString("Vendor Zip: ", true);
			String phoneNumber = Console.getString("Vendor Phone Number: ", true);
			String email = Console.getString("Vendor Email: ", true);
			boolean isPreApproved = Console.getBoolean("Vendor PreApproved: ");
			Vendor p = new Vendor(vCode, name, address, city, state, zip, phoneNumber, email, isPreApproved);
			vendorDB.add(p);
		} else if (table.equalsIgnoreCase("Products")) {
			int vendorID = Console.getInt("Vendor ID: ");
			String partNumber = Console.getString("Part Number: ", true);
			String name = Console.getString("Part Name: ", true);
			double price = Console.getDouble("Price: ");
			String unit = Console.getString("Unit: ");
			String photoPath = Console.getString("Photo Path: ");
			Product p = new Product(vendorID, partNumber, name, price, unit, photoPath);
			productDB.add(p);
		} else if (table.equalsIgnoreCase("purreq")) {
			int userID = Console.getInt("User ID: ");
			String description = Console.getString("Description: ", true);
			String justification = Console.getString("Justification: ", true);
			String dn = Console.getString("Date Needed(MM-DD-YYYY): ", true);
			String[] d = dn.split("-");
			Integer[] dI = { Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]) };
			LocalDate dateNeeded = LocalDate.of(dI[2], dI[0], dI[1]);
			String deliveryMode = Console.getString("Delivery Mode: ", true);
			String status = Console.getString("Status: ", true);
			double total = 0;
			LocalDate submittedDate = LocalDate.now();
			String reasonForRejection = null;
			PurchaseRequest p = new PurchaseRequest(userID, description, justification, dateNeeded, deliveryMode,
					status, total, submittedDate, reasonForRejection);
			purchaseRequestDB.add(p);
		} else {
			int purchaseRequestID = Console.getInt("Purchase Request ID: ");
			int productID = Console.getInt("Product ID: ");
			int quantity = Console.getInt("Quantity: ");
			PurchaseRequestLineItem p = new PurchaseRequestLineItem(purchaseRequestID, productID, quantity);
			purchaseRequestLineItemDB.add(p);
		}

	}

	private static void displayPurchaseRequests() {
		System.out.println("Purchase Requests");
		System.out.println("==========================================");
		List<PurchaseRequest> purchaseRequests = purchaseRequestDB.getAll();
		List<PurchaseRequestLineItem> purchaseRequestLineItems = purchaseRequestLineItemDB.getAll();
		// List<Product> products = productDB.getAll();
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
			for (PurchaseRequestLineItem d : purchaseRequestLineItems) {
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
