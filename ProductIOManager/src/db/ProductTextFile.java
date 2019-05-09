package db;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import business.Product;

public class ProductTextFile implements DAO<Product> {
	private List<Product> products = null;
	private Path productsPath = null;
	private File productsFile = null;
	private final String FIELD_SEP = "\t";

	public ProductTextFile() {
		productsPath = Paths.get("products.txt");
		productsFile = productsPath.toFile();
		products = this.getAll();
	}

	@Override
	public List<Product> getAll() {
		if (products != null) {
			return products;
		}

		products = new ArrayList<>();
		if (Files.exists(productsPath)) {
			try (BufferedReader in = new BufferedReader(new FileReader(productsFile))) {
				String line = in.readLine();
				while (line != null) {
					String[] fields = line.split(FIELD_SEP);
					String code = fields[0];
					String desc = fields[1];
					String price = fields[2];

					Product p = new Product(code, desc, Double.parseDouble(price));
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

	@Override
	public Product get(String code) {
		for (Product p : products) {
			if (p.getCode().equalsIgnoreCase(code)) {
				return p;
			}
		}
		return null;
	}

	private boolean saveAll() {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(productsFile)))) {
			for (Product p: products) {
				out.print(p.getCode() + FIELD_SEP);
				out.print(p.getDescription() + FIELD_SEP);
				out.println(p.getPrice());
			}
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public boolean add(Product t) {
		products.add(t);
		return this.saveAll();
	}

	@Override
	public boolean delete(Product t) {
		products.remove(t);
		return this.saveAll();
	}

	@Override
	public boolean update(Product t) {
		Product oldProduct = this.get(t.getCode());
		int i = products.indexOf(oldProduct);
		products.remove(i);

		products.add(i, t);

		return this.saveAll();
	}

}
