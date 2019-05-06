package ui;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import business.Product;

public class FileDemoApp {
	public static void main(String[] args) {
		System.out.println("Welcome");

		try {
			//Define a folder on the HDD
			String dirString = "c:/temp/subfolder1";
			Path dirPath = Paths.get(dirString);

			if (Files.notExists(dirPath)) {
				Files.createDirectories(dirPath);
				System.out.println("Directory " + dirString + " successfully created.");
			} else {
				System.out.println("Dirrctory " + dirString + " already exists.");
			}
			
			
			//Define a file on a folder
			String fileString = "products.txt";
			Path filePath = Paths.get(dirString, fileString);
			if (Files.notExists(filePath)) {
				Files.createFile(filePath);
				System.out.println("File " + fileString + " successfully created.");
			} else {
				System.out.println("File " + fileString + " already exists.");
			}
			
			//Print file info
			System.out.println("File name:     " + filePath.getFileName());
			System.out.println("Absolute path: " + filePath.toAbsolutePath());
			System.out.println("Is writable:   " + Files.isWritable(filePath));
			
			System.out.println("Files in a directory.");

			if (Files.exists(dirPath) && Files.isDirectory(dirPath)) {
				System.out.println("Directory:     " + dirPath.toAbsolutePath());
				System.out.println("Files:     ");
				DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath);
				for (Path p : dirStream) {
					if (Files.isRegularFile(p)) {
						System.out.println("      " + p.getFileName());
					}
				}
			}
			
			//Write to the file
			Path productsPath = Paths.get("products.txt");
			//This is a different products.txt from the example above
			
			File productsFile = productsPath.toFile();
			
			//Open an output stream
			PrintWriter out = new PrintWriter(
							  new BufferedWriter(
							  new FileWriter(productsFile)));
			
			//Write data to the stream
			out.println("java\tMurach's Java Programming\t57.50");
			//Close the stream to avoid a resource leak
			out.close();
			
			BufferedReader in = new BufferedReader(
								new FileReader(productsFile));
			//Read data from the stream and print to console
			String line = in.readLine();
			System.out.println(line);
			
			//Bonus instruction - map this line to a product
			String[] fields = line.split("\t");
			String code = fields[0];
			String desc = fields[1];
			double price = Double.parseDouble(fields[2]);
			
			Product p = new Product(code, desc, price);
			System.out.println();
			//Close the input stream and free system resources
			in.close();
								
		} catch (IOException e) {
			System.out.println("Bad directory path entered.");
			e.printStackTrace();
		}
		System.out.println("Goodbye");
	}
}
