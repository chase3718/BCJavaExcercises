import java.util.Scanner;

public class spongeText {
	public static String alterText (String str) {
		str = str.toLowerCase();
		String fin = "";
		int x = 0;
		for (int i = 0; i < str.length(); i++) {
			if (x == 0) {
				x = (int)(Math.random()*2.75);
			} else {
				x = (int)(Math.random()*1.25);
			}
			
			if (x == 0) {
				fin += str.substring(i,i+1);
			} else {
				fin += str.substring(i,i+1).toUpperCase();
			}
		}
		return fin;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Spongebob text generator.\n");/** Make sure to import java.util.scanner. Must have a Scanner called sc.**/

		Scanner sc = new Scanner(System.in);
		boolean run = true;
		boolean cont = true;
		String choice;

		while (run) {
			System.out.print("Text: ");
			String text = sc.nextLine();
			
			System.out.println(alterText(text));
			
			System.out.print("Continue? (y/n) ");
			choice = sc.nextLine();
			System.out.println();
			
			cont = true;

			while (cont) {
				if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
					run = false;
					sc.close();
					System.out.println("OK, Goodbye.");
					cont = false;
				} else if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
					cont = false;
				} else {
					System.out.print("Must be in the form of y/n or yes/no.\n\nContinue? (y/n) ");
					choice = sc.nextLine();
					System.out.println();
				}
			}
		}
	}

}
