package app;

import business.Bart;
import business.Lisa;
import business.Player;
import business.Player1;
import business.Roshambo;
import ui.Console;

public class RPSApp {
	public static void main(String[] args) {

		System.out.println("Welcome to the game of Roshambo.\n");

		Console in = new Console();

		Player1 player = new Player1();

		Player comp;

		do {

			switch (in.getStringOptions("\nWould you like to play against Bart or Lisa? (B/L) ", true, "b", "l")) {
			case "B":
				comp = new Bart();
				break;
			case "L":
				comp = new Lisa();
				break;
			default:
				continue;
			}

			player.setPlayer(Player.generateRoshambo(
					in.getStringOptions("\nRock, Paper, or Scissors? (R/P/S) ", true, "r", "p", "s")));

			System.out.println(player);
			System.out.println(comp);

			System.out.println(
					Roshambo.getWinner(player.getNameUpper(), comp.getName(), player.getPlayer(), comp.getPlayer()));

		} while (in.getStringOptions("\nPlayer again? (y/n) ", true, "y", "n").equals("Y"));

		System.out.println("\nBye");

	}
}
