import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileCleanerApp {
	public static void main (String[] args) {
		System.out.println("Welcome to the File Cleaner App\n");
		try {
			Path prospectsPath = Paths.get("prospects.csv");
			File prospectsFile = prospectsPath.toFile();
			
			//open an input stream
			BufferedReader in = new BufferedReader(
								new FileReader(prospectsFile));
			
			
			String line = in.readLine();
			List<String> p = new ArrayList<>();
			while (line != null) {
				p.add(line);
				line = in.readLine();
			}
			
			List<Person> prospects = new ArrayList<>();
			int count = 0;
			for (String i: p) {
				String[] s = p.get(count).split(",");
				prospects.add(new Person(s[0],s[1],s[2]));
				count ++;
			}
			
			String fileString = "prospects_clean.csv";
			Path filePath = Paths.get(fileString);
			if (Files.notExists(filePath)) {
				Files.createFile(filePath);
				System.out.println("File " + fileString + " successfully created.");
			} else {
				System.out.println("File " + fileString + " already exists.");
			}
			
			//Write to the file			
			Path prospectsCleanPath = Paths.get("prospects_clean.csv");
			//This is a different products.txt from the example above
			
			File prospectsCleanFile = prospectsCleanPath.toFile();
			//Open an output stream
			PrintWriter out = new PrintWriter(
							  new BufferedWriter(
							  new FileWriter(prospectsCleanFile)));
			
			//Write data to the stream
			//out.println("java\tMurach's Java Programming\t57.50");
			for (int i = 0; i < prospects.size(); i++) {
				out.println(prospects.get(i).toString());
			}
			System.out.println("Prospects cleaned");
			//Close the stream to avoid a resource leak
			out.close();
			
			/*
			for (int i = 1; i < prospects.size(); i++) {
				System.out.println(prospects.get(i).toString());	
			}
			System.out.println(prospects.get(1).toString());
			*/
			in.close();
		} catch (IOException e) {
			System.out.println("Bad directory path entered.");
			e.printStackTrace();
		}
		System.out.println("Goodbye");
	}
}
