
public class RPSApp {
	public static void main(String[] args) {
		System.out.println("Welcome to the game of Roshambo.\n");

		Console in = new Console();

		String choice = "y";
		
		Player1 player = new Player1();
		
		Player comp;
		
		while (choice.equalsIgnoreCase("y")) {

			String opp = in.getStringOptions("\nWould you like to play against Bart or Lisa? (B/L) ", "b", "l", true);	

			if (opp.equalsIgnoreCase("b")) {
				comp = new Bart();
			} else {
				comp = new Lisa();
			}

			player.setPlayer(Player.generateRoshambo(in.getString("\nRock, Paper, or Scissors? (R/P/S) ")));

			System.out.println(player);
			System.out.println(comp);

			String winner = Roshambo.getWinner(player.getName(), comp.getName(), player.getPlayer(), comp.getPlayer());

			System.out.println(winner);

			choice = in.getString("\nPlayer again? (y/n) ");

		}

		System.out.println("\nBye");

	}
}
