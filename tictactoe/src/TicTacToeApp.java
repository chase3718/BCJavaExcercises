
public class TicTacToeApp {
	public static void main(String[] args) {
		System.out.println("Welcome to Tic Tac Toe\n");

		Console in = new Console();

		String[] turn = new String[2];

		boolean ai = false;
		String dif = "e";

		turn[0] = "X";
		turn[1] = "O";

		do {
			boolean game = true;

			int boardSize = in.getInt("Board Size: ", 0, 21);

			String[][] b = new String[boardSize][boardSize];

			Board playArea = new Board(b);
			playArea.cleanBoard();

			ai = in.getStringOptions("Play against AI? (y/n) ", true, "y", "n").equals("Y");
			if (ai)
				dif = /*in.getStringOptions("Difficulty (e/m/h) ", true, "e", "m", "h")*/ "e";

			double counter = 0;

			playArea.drawBoard();

			while (game) {

				if (counter % 2 == 0) {
					playArea.playBoard("X");
				} else {
					if (ai) {
						playArea.playBoard("O", dif);
					} else {
						playArea.playBoard("O");
					}
				}
				playArea.drawBoard();
				game = !playArea.checkBoard("X");
				if (game == true) {
					game = !playArea.checkBoard("O");
				}
				counter++;
			}
		} while (in.getStringOptions("Play Again? (y/n) ", true, "y", "n").equals("Y"));
	}
}
