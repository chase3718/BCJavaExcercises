
public class ArrowHeadApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the Arrow Head App\n");

		Console input = new Console();
		String choice = "y";

		while (choice.equalsIgnoreCase("y")) {
			int n = input.getInt("Enter a positive integer ");
			n = Math.abs(n);
			int s = (n / 2);

			for (int i = s; i >= -s; i--) {
				for (int x = 0; x <= (Math.abs(s) - Math.abs(i)); x++) {
					System.out.print(">");
				}
				System.out.println();
			}
			
			choice = input.getString("Continue? (y/n) ");
		}

		System.out.println("Goodbye");
	}
}
