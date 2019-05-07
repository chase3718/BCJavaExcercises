package ui;

import business.Product;
import db.DAO;
import db.ProductTextFile;

public class ProductIOManagerApp {
	
	private static DAO<Product> productFile = new ProductTextFile();
	
	public static void main (String[] args) {
		System.out.println("Welcome to the Product Manager - Text File Edition");
		
		System.out.println(productFile.getAll());
		
		System.out.println("Bye");
	}
}
