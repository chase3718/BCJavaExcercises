package com.prs.db;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.prs.business.Product;
import com.prs.business.Vendor;



public class ProductTextFile {
	private List<Product> products = null;
	private Path productsPath = null;
	private File productsFile = null;
	private final static String FIELD_SEP = ",";

	public ProductTextFile() {
		productsPath = Paths.get("products.csv");
		productsFile = productsPath.toFile();
	}
	
	public ProductTextFile(String file) {
		productsPath = Paths.get(file);
		productsFile = productsPath.toFile();
	}

	public List<Product> getAll(Vendor v) {
		if (products != null) {
			return products;
		}

		products = new ArrayList<>();
		if (Files.exists(productsPath)) {
			try (BufferedReader in = new BufferedReader(new FileReader(productsFile))) {
				String line = in.readLine();
				while (line != null) {
					String[] fields = line.split(FIELD_SEP);
					//int vendorID = Integer.parseInt(fields[1]);
					String partNumber = fields[0];
					partNumber.trim();
					String name = fields[1];
					name.trim();
					double price = Double.parseDouble(fields[2]);
					String unit;
					if (fields[3] != null) {
						unit = fields[3];
					} else {
						unit = null;
					}
					unit.trim();
					if (unit.equals("")) {
						unit = null;
					}
					String photoPath;
					if (fields[4] != null) {
						photoPath = fields[4];
					} else {
						photoPath = null;
						
					}
					photoPath.trim();
					Product p = new Product(v, partNumber, name, price, unit, photoPath);
					//Vendor vendorID, String partNumber, String name, double price, String unit, String photoPath
					products.add(p);

					line = in.readLine();
				}
			} catch (IOException e) {
				System.out.println(e);
				return null;
			}
		} else {
			System.out.println(productsPath.toAbsolutePath() + " doesn't exist.");
			return null;
		}
		return products;
	}

}
