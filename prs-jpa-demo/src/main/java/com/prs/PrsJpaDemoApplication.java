package com.prs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prs.business.Product;
import com.prs.business.User;
import com.prs.business.Vendor;
import com.prs.business.PurchaseRequest;
import com.prs.business.PurchaseRequestLineItem;
import com.prs.db.ProductDB;
import com.prs.db.ProductTextFile;
import com.prs.db.PurchaseRequestDB;
import com.prs.db.PurchaseRequestLineItemDB;
import com.prs.db.UserDB;
import com.prs.db.VendorDB;
import com.prs.util.Console;

@SpringBootApplication
public class PrsJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrsJpaDemoApplication.class, args);
		System.out.println("Welcome to the Purchase Request System\n");

		displayMenu();

		String choice = Console.getString("Enter a command: ", true, "list", "sel", "add", "del", "help", "exit",
				"upd");

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
				} else if (listChoice.equalsIgnoreCase("lineitem")) {
					displayLineItems();
				}
			} else if (choice.equalsIgnoreCase("sel")) {
				displaySelectMenu();
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
						Vendor vId = selectFromVendors(Console.getInt("Choose a vendor by ID: "));

						ProductTextFile ptf = new ProductTextFile();
						addToTable(ptf.getAll(vId));
					} else {
						addToTable(addChoice);
					}
				} else {
					addToTable(addChoice);
				}
			} else if (choice.equalsIgnoreCase("del")) {
				displayDeleteMenu();
				String delChoice = Console.getString("Enter a table to delete from: ", true, "users", "vendors",
						"products", "purReq", "lineitem");
				delFromTable(delChoice);
			} else if (choice.equalsIgnoreCase("help")) {
				displayMenu();
			} else if (choice.equalsIgnoreCase("upd")) {
				displayUpdateMenu();
				String upChoice = Console.getString("Enter a table to add to: ", true, "users", "vendors", "products",
						"purReq", "lineitem");
				updateTable(upChoice);
			}

			choice = Console.getString("Enter a command: ", true, "list", "sel", "add", "del", "help", "exit", "upd");

		}
		System.out.println("Bye");
	}

	private static Vendor selectFromVendors(int id) {
		return VendorDB.get(id);
	}

	private static void updateTable(String c) {
		if (c.equalsIgnoreCase("users")) {
			displayUsers();
			int u = Console.getInt("Choose a user to update by ID: ", 0, UserDB.getAll().size() + 1);
			User user = UserDB.get(u);
			String field = Console.getString("Which field would you like to update? ", true, "userName", "password",
					"firstName", "lastName", "phoneNumber", "email", "isReviewer", "isAdmin");
			if (field.equalsIgnoreCase("username")) {
				String us = Console.getString("New Username: ", true);
				user.setUserName(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("password")) {
				String us = Console.getString("New Password: ", true);
				user.setPassword(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("firstname")) {
				String us = Console.getString("New First Name: ", true);
				user.setFirstName(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("lastname")) {
				String us = Console.getString("New Last Name: ", true);
				user.setLastName(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("phonenumber")) {
				String us = Console.getString("New Phone Number: ", true);
				user.setPhoneNumber(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("email")) {
				String us = Console.getString("New Email: ", true);
				user.setEmail(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("isreviewer")) {
				Boolean us = Console.getBoolean("New Reviewer: ");
				user.setReviewer(us);
				UserDB.update(user);
			} else if (field.equalsIgnoreCase("isadmin")) {
				Boolean us = Console.getBoolean("New Admin: ");
				user.setAdmin(us);
				UserDB.update(user);
			}
		} else if (c.equalsIgnoreCase("vendors")) {
			displayVendors("n");
			int u = Console.getInt("Choose a vendor to update by ID: ", 0, UserDB.getAll().size() + 1);
			Vendor vendor = VendorDB.get(u);
			String field = Console.getString("Which field would you like to update? ", true, "code", "name", "address",
					"city", "state", "zip", "phonenumber", "email", "ispreapproved");
			if (field.equalsIgnoreCase("code")) {
				String us = Console.getString("New Code: ", true);
				vendor.setCode(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("name")) {
				String us = Console.getString("New Name: ", true);
				vendor.setName(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("address")) {
				String us = Console.getString("New Address: ", true);
				vendor.setAddress(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("city")) {
				String us = Console.getString("New City: ", true);
				vendor.setCity(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("state")) {
				String us = Console.getString("New State: ");
				vendor.setState(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("phonenumber")) {
				String us = Console.getString("New Phone Number: ", true);
				vendor.setPhoneNumber(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("zip")) {
				String us = Console.getString("New zip: ", true);
				vendor.setZip(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("email")) {
				String us = Console.getString("New Email: ", true);
				vendor.setEmail(us);
				VendorDB.update(vendor);
			} else if (field.equalsIgnoreCase("ispreapproved")) {
				Boolean us = Console.getBoolean("is PreApproved: ");
				vendor.setPreApproved(us);
				VendorDB.update(vendor);
			}
		} else if (c.equalsIgnoreCase("products")) {
			displayProducts();
			int u = Console.getInt("Choose a product to update by ID: ", 0, ProductDB.getAll().size() + 1);
			Product product = ProductDB.get(u);
			String field = Console.getString("Which field would you like to update? ", true, "vendor", "partnumber",
					"name", "price", "unit", "photopath");
			if (field.equalsIgnoreCase("vendor")) {
				displayVendors("n");
				int us = Console.getInt("New Vendor(by ID): ", 0, VendorDB.getAll().size() + 1);
				product.setVendor(VendorDB.get(us));
				ProductDB.update(product);
			} else if (field.equalsIgnoreCase("partnumber")) {
				String us = Console.getString("New Part Number: ", true);
				product.setPartNumber(us);
				ProductDB.update(product);
			} else if (field.equalsIgnoreCase("name")) {
				String us = Console.getString("New Name: ", true);
				product.setName(us);
				ProductDB.update(product);
			} else if (field.equalsIgnoreCase("price")) {
				double us = Console.getDouble("New Price: ", 0, Double.POSITIVE_INFINITY);
				product.setPrice(us);
				ProductDB.update(product);
			} else if (field.equalsIgnoreCase("unit")) {
				String us = Console.getString("New Unit: ");
				product.setUnit(us);
				ProductDB.update(product);
			} else if (field.equalsIgnoreCase("photopath")) {
				String us = Console.getString("New Photopath: ", true);
				product.setPhotoPath(us);
				ProductDB.update(product);
			}
		} else if (c.equalsIgnoreCase("purreq")) {
			displayPurchaseRequests();
			int u = Console.getInt("Choose a purchase request to update by ID: ", 0, UserDB.getAll().size() + 1);
			PurchaseRequest purchaseRequest = PurchaseRequestDB.get(u);
			String field = Console.getString("Which field would you like to update? ", true, "user", "desc", "just",
					"dateneeded", "delivmode", "status", "rejection");
			if (field.equalsIgnoreCase("user")) {
				displayUsers();
				int us = Console.getInt("New User(by ID): ", 0, UserDB.getAll().size() + 1);
				purchaseRequest.setUser(UserDB.get(us));
				PurchaseRequestDB.update(purchaseRequest);
			} else if (field.equalsIgnoreCase("desc")) {
				String us = Console.getString("New Description: ", true);
				purchaseRequest.setDescription(us);
				PurchaseRequestDB.update(purchaseRequest);
			} else if (field.equalsIgnoreCase("just")) {
				String us = Console.getString("New Justification: ", true);
				purchaseRequest.setJustification(us);
				PurchaseRequestDB.update(purchaseRequest);
			} else if (field.equalsIgnoreCase("dateNeeded")) {
				String dn = Console.getString("New Date Needed(MM-DD-YYYY): ", true);
				String[] d = dn.split("-");
				Integer[] dI = { Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]) };
				LocalDate us = LocalDate.of(dI[2], dI[0], dI[1]);
				purchaseRequest.setDateNeeded(us);
				PurchaseRequestDB.update(purchaseRequest);
			} else if (field.equalsIgnoreCase("delivmode")) {
				String us = Console.getString("New Deleivery Mode: ");
				purchaseRequest.setDeliveryMode(us);
				PurchaseRequestDB.update(purchaseRequest);
			} else if (field.equalsIgnoreCase("status")) {
				String us = Console.getString("New Status: ", true);
				purchaseRequest.setStatus(us);
				PurchaseRequestDB.update(purchaseRequest);
			} else if (field.equalsIgnoreCase("rejection")) {
				String us = Console.getString("New Reason for Rejection: ", true);
				purchaseRequest.setReasonForRejection(us);
				PurchaseRequestDB.update(purchaseRequest);
			}
		} else if (c.equalsIgnoreCase("lineitem")) {
			displayLineItems();
			int u = Console.getInt("Choose a purchase request to update by ID: ", 0, UserDB.getAll().size() + 1);
			PurchaseRequestLineItem purchaseRequestLineItem = PurchaseRequestLineItemDB.get(u);
			String field = Console.getString("Which field would you like to update? ", true, "product", "quantity");
			if (field.equalsIgnoreCase("product")) {
				displayProducts();
				int us = Console.getInt("New Product(by ID): ", 0, ProductDB.getAll().size() + 1);
				purchaseRequestLineItem.setProduct(ProductDB.get(us));
				PurchaseRequestLineItemDB.update(purchaseRequestLineItem);
			} else if (field.equalsIgnoreCase("quantity")) {
				int us = Console.getInt("New Quantity: ", 0, Integer.MAX_VALUE);
				purchaseRequestLineItem.setQuantity(us);
				PurchaseRequestLineItemDB.update(purchaseRequestLineItem);
			}
		}
	}

	private static void selectFromTable(String table) {
		if (table.equalsIgnoreCase("users")) {
			User p = UserDB.get(Console.getInt("Select a user by ID: ", 0, UserDB.getAll().size() + 1));
			System.out.println("User");
			System.out.println("==========================================");
			StringBuilder sb = new StringBuilder();
			sb.append("=Name: " + p.getFirstName() + " " + p.getLastName() + "=\n");
			sb.append("\tUser ID:       " + p.getId() + "\n");
			sb.append("\tUsername:      " + p.getUserName() + "\n");
			sb.append("\tPassword:      " + p.getPasswordStars() + "\n");
			sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
			sb.append("\tEmail:         " + p.getEmail() + "\n");
			sb.append("\tIs a Reviewer: " + p.isReviewer() + "\n");
			sb.append("\tIs an Admin:   " + p.isAdmin() + "\n");
			sb.append("\n");
			System.out.println(sb.toString());
		} else if (table.equalsIgnoreCase("vendors")) {
			Vendor p = VendorDB.get(Console.getInt("Select a vendor by ID: ", 0, VendorDB.getAll().size() + 1));
			String c = Console.getString("Display with products(y/n): ", true, "y", "n");
			System.out.println("Vendors");
			System.out.println("==========================================");
			StringBuilder sb = new StringBuilder();
			if (c.equalsIgnoreCase("n")) {

				sb.append("=Name: " + p.getName() + "=\n");
				sb.append("\tid:            " + p.getId() + "=\n");
				sb.append("\tVendor Code:   " + p.getCode() + "\n");
				sb.append("\tAddress:       " + p.getAddress() + ", " + p.getCity() + " " + p.getState() + " "
						+ p.getZip() + "\n");
				sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
				sb.append("\tEmail:         " + p.getEmail() + "\n");
				sb.append("\tPreApproved:   " + p.isPreApproved() + "\n");
				sb.append("\n");

			} else {
				List<Product> products = ProductDB.getAll();

				sb.append("=Name: " + p.getName() + "=\n");
				sb.append("\tid:            " + p.getId() + "=\n");
				sb.append("\tVendor Code:   " + p.getCode() + "\n");
				sb.append("\tAddress:       " + p.getAddress() + ", " + p.getCity() + " " + p.getState() + " "
						+ p.getZip() + "\n");
				sb.append("\tPhone Number:  " + p.getPhoneNumber() + "\n");
				sb.append("\tEmail:         " + p.getEmail() + "\n");
				sb.append("\tPreApproved:   " + p.isPreApproved() + "\n");
				sb.append("\t=Products=\n");
				for (Product n : products) {
					if (n.getVendor().getId() == p.getId()) {
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
			Product p = ProductDB.get(Console.getInt("Select a product by ID: ", 0, ProductDB.getAll().size() + 1));
			System.out.println("Products");
			System.out.println("==========================================");
			StringBuilder sb = new StringBuilder();

			sb.append("\tPart Name:   " + p.getName() + "\n");
			sb.append("\tid:          " + p.getId() + "=\n");
			sb.append("\tPart Number: " + p.getPartNumber() + "\n");
			sb.append("\tUnit:        " + p.getUnit() + "\n");
			sb.append("\tPrice:       " + p.getPriceFormatted() + "\n");
			sb.append("\tVendor:      " + p.getVendor().getName() + "\n");
			sb.append("\n");

			System.out.println(sb.toString());
		} else if (table.equalsIgnoreCase("purreq")) {
			System.out.println("Purchase Requests");
			System.out.println("==========================================");

			List<PurchaseRequestLineItem> purchaseRequestLineItems = PurchaseRequestLineItemDB.getAll();
			// List<Product> products = productDB.getAll();
			StringBuilder sb = new StringBuilder();
			PurchaseRequest p = PurchaseRequestDB
					.get(Console.getInt("Select a purchase request by ID: ", 0, PurchaseRequestDB.getAll().size() + 1));
			User u = UserDB.get(p.getUser().getId());
			sb.append("=User: " + u.getUserName() + "=\n");
			sb.append("\tid:            " + p.getId() + "\n");
			sb.append("\tDescription:   " + p.getDescription() + "\n");
			sb.append("\tJustification: " + p.getJustification() + "\n");
			DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
			sb.append("\tDate Needed:   " + dtf.format(p.getDateNeeded()) + "\n");
			sb.append("\tDelivery Mode: " + p.getDeliveryMode() + "\n");
			sb.append("\tStatus:        " + p.getStatus() + "\n");
			sb.append("\t=Products=\n");
			for (PurchaseRequestLineItem d : purchaseRequestLineItems) {
				if (d.getPurchaseRequest().getId() == p.getId()) {
					Product n = ProductDB.get(d.getProduct().getId());
					sb.append("\t\tPart Name:   " + n.getName() + "\n");
					sb.append("\t\tid:          " + n.getId() + "\n");
					sb.append("\t\tPart Number: " + n.getPartNumber() + "\n");
					sb.append("\t\tUnit:        " + n.getUnit() + "\n");
					sb.append("\t\tPrice:       " + n.getPriceFormatted() + "\n");
					sb.append("\t\tVendor:      " + n.getVendor().getName() + "\n");
					double pr = n.getPrice() * d.getQuantity();
					sb.append("\tTotal:         " + pr);
				}

				sb.append("\n");
			}
			System.out.println(sb.toString());
		} else if (table.equalsIgnoreCase("lineitem")) {
			System.out.println("Purchase Request Line Items");
			System.out.println("==========================================");

			StringBuilder sb = new StringBuilder();
			PurchaseRequestLineItem p = PurchaseRequestLineItemDB.get(Console.getInt(
					"Select a purchase request line item by ID: ", 0, PurchaseRequestLineItemDB.getAll().size() + 1));

			sb.append("=Product:    " + p.getProduct().getName() + "=\n");
			sb.append("\tid:        " + p.getId() + "\n");
			sb.append("\tQuantity:  " + p.getQuantity() + "\n");
			sb.append("\tPrice:     " + (p.getQuantity() * p.getProduct().getPrice()) + "\n");
			sb.append("\tUser:      " + p.getPurchaseRequest().getUser().getUserName() + "\n");
			sb.append("\n");

			System.out.println(sb.toString());
		}
	}

	private static void delFromTable(String table) {
		if (table.equalsIgnoreCase("users")) {
			UserDB.delete(UserDB.get(Console.getInt("Choose a user ID to delete: ")));
		} else if (table.equalsIgnoreCase("vendors")) {
			VendorDB.delete(VendorDB.get(Console.getInt("Choose a vendor ID to delete: ")));
		} else if (table.equalsIgnoreCase("products")) {
			ProductDB.delete(ProductDB.get(Console.getInt("Choose a product ID to delete: ")));
		} else if (table.equalsIgnoreCase("purreq")) {
			PurchaseRequestDB.delete(PurchaseRequestDB.get(Console.getInt("Choose a Purchase Request ID to delete: ")));
		} else {
			PurchaseRequestLineItemDB.delete(
					PurchaseRequestLineItemDB.get(Console.getInt("Choose a Purchase Request Line Item to delete: ")));
		}
	}

	private static void addToTable(List<Product> table) {
		for (Product p : table) {
			ProductDB.insert(p);
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
			UserDB.insert(p);
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
			VendorDB.insert(p);
		} else if (table.equalsIgnoreCase("Products")) {
			int vendorID = Console.getInt("Vendor ID: ");
			String partNumber = Console.getString("Part Number: ", true);
			String name = Console.getString("Part Name: ", true);
			double price = Console.getDouble("Price: ");
			String unit = Console.getString("Unit: ");
			String photoPath = Console.getString("Photo Path: ");
			Product p = new Product(VendorDB.get(vendorID), partNumber, name, price, unit, photoPath);
			ProductDB.insert(p);
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
			PurchaseRequest p = new PurchaseRequest(UserDB.get(userID), description, justification, dateNeeded,
					deliveryMode, status, total, submittedDate, reasonForRejection);
			PurchaseRequestDB.insert(p);
		} else {
			int purchaseRequestID = Console.getInt("Purchase Request ID: ");
			int productID = Console.getInt("Product ID: ");
			int quantity = Console.getInt("Quantity: ");
			PurchaseRequestLineItem p = new PurchaseRequestLineItem(PurchaseRequestDB.get(purchaseRequestID),
					ProductDB.get(productID), quantity);
			PurchaseRequestLineItemDB.insert(p);
		}

	}

	private static void displayPurchaseRequests() {
		System.out.println("Purchase Requests");
		System.out.println("==========================================");
		List<PurchaseRequest> purchaseRequests = PurchaseRequestDB.getAll();
		List<PurchaseRequestLineItem> purchaseRequestLineItems = PurchaseRequestLineItemDB.getAll();
		// List<Product> products = productDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (PurchaseRequest p : purchaseRequests) {
			sb.append(p);
			sb.append("\t=Products=\n");
			for (PurchaseRequestLineItem d : purchaseRequestLineItems) {
				if (d.getPurchaseRequest().getId() == p.getId()) {
					sb.append(d);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void displayLineItems() {
		System.out.println("Purchase Request Line Items");
		System.out.println("==========================================");
		List<PurchaseRequestLineItem> prli = PurchaseRequestLineItemDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (PurchaseRequestLineItem p : prli) {
			sb.append(p);
		}
		System.out.println(sb.toString());
	}
	
	private static void displayUsers() {
		System.out.println("Users");
		System.out.println("==========================================");
		List<User> users = UserDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (User p : users) {
			sb.append(p);
		}
		System.out.println(sb.toString());
	}

	private static void displayVendors(String c) {
		System.out.println("Vendors");
		System.out.println("==========================================");
		List<Vendor> vendors = VendorDB.getAll();
		StringBuilder sb = new StringBuilder();
		if (c.equalsIgnoreCase("n")) {

			for (Vendor p : vendors) {
				sb.append(p);
			}
		} else {
			List<Product> products = ProductDB.getAll();
			for (Vendor p : vendors) {
				sb.append(p);
				for (Product n : products) {
					if (n.getVendor().getId() == p.getId()) {
						sb.append("\t" + n);
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
		List<Product> products = ProductDB.getAll();
		StringBuilder sb = new StringBuilder();
		for (Product p : products) {
			sb.append(p.toString());
		}
		System.out.println(sb.toString());
	}
	
	private static void displayDeleteMenu() {
		System.out.println("List of Tables");
		System.out.println("users   - Delete a user");
		System.out.println("vendors - Delete a vendor");
		System.out.println("products- Delete a product");
		System.out.println("purReq  - Delete a purchase request");
		System.out.println("lineitem- Delete a line item");
	}
	
	private static void displayUpdateMenu() {
		System.out.println("List of Tables");
		System.out.println("users   - Update a user");
		System.out.println("vendors - Update a vendor");
		System.out.println("products- Update a product");
		System.out.println("purReq  - Update a purchase request");
		System.out.println("lineitem- Update a line item");
	}
	
	private static void displaySelectMenu() {
		System.out.println("List of Tables");
		System.out.println("users   - Display a user");
		System.out.println("vendors - Display a vendor");
		System.out.println("products- Display a product");
		System.out.println("purReq  - Display a purchase request");
		System.out.println("lineitem- Display a line item");
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
		System.out.println("upd     - Update a table entry");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application");
	}
}
