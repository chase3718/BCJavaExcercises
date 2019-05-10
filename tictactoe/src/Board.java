
public class Board {

	private static Console in = new Console();
	private String board[][];

	public Board(String[][] board) {
		this.board = board;
	}

	public void drawBoard() {
		String[][] in = this.board;
		for (int row = 0; row < in.length; row++) {
			drawLine(in[row].length);
			for (int col = 0; col < in[row].length; col++) {
				System.out.print("| " + in[row][col] + " ");
			}
			System.out.println("|");
		}
		drawLine(in[0].length);
	}

	public void drawLine(int l) {
		for (int i = 0; i < l; i++) {
			System.out.print("+");
			for (int x = 0; x < 3; x++) {
				System.out.print("-");
			}
		}
		System.out.println("+");
	}
	
	public void playBoard(String str, String dif) {
		boolean validPlay = false;
		int row = 0;
		int col = 0;
		while (!validPlay) {
			if (dif.equalsIgnoreCase("E")) {
				row = (int)(Math.random() * this.board.length);
				col = (int)(Math.random() * this.board.length);
				if (this.board[row][col].equals(" ")) {
					this.board[row][col] = str;
					validPlay = true;
					break;
				}
			} else if (dif.equalsIgnoreCase("M")) {
				
			}
		}
	}
	
	public void playBoard(String str) {
		String rowAmnt = "(";
		String colAmnt = "(";
		for (int x = 0; x < this.board.length; x++) {
			if (x > 0) {
				rowAmnt += ", ";
			}
			rowAmnt += (x + 1);
		}
		for (int y = 0; y < this.board[0].length; y++) {
			if (y > 0) {
				colAmnt += ", ";
			}
			colAmnt += (y + 1);
		}
		rowAmnt += ")";
		colAmnt += ")";
		boolean isValid = false;
		while (!isValid) {
			int row = in.getInt("\n" + str + " Pick a row " + rowAmnt + " ", 0, this.board.length + 1);
			int col = in.getInt("\n" + str + " pick a column " + colAmnt + " ", 0, this.board.length + 1);
			if (this.board[row-1][col-1].equals(" ")) {
				this.board[row-1][col-1] = str;
				isValid = true;
			} else {
				System.out.println("Spot already taken, try again.");
			}
		}
	}
	
	public void cleanBoard() {
		for (int x = 0; x < this.board.length; x++) {
			for (int y = 0; y < this.board[0].length; y++) {
				this.board[x][y] = " ";
			}
		}
	}
	
	public boolean checkBoard(String p) {
		boolean stop = false;
		boolean winner = true;
		int y1 = 0;
		for (int x = 0; x < this.board.length; x ++) {
			for (int y = 0; y < this.board[x].length; y++) {
				y1 = y;
				if (!this.board[x][y].equals(p)) {
					winner = false;
					break;
				}
			}
			if (winner) {
				System.out.println(p + " wins!");
				return true;
			}
			winner = true;
		}
		
		winner = true;
		for (int x = 0; x < this.board[0].length; x++) {
			for (int y = 0; y < board.length; y++) {
				y1 = y;
				if (!this.board[y][x].equals(p)) {
					winner = false;
					break;
				}
			}
			if (winner) {
				System.out.println(p + " wins!");
				return true;
			}
			winner = true;
		}
		
		winner = true;
		for (int x = 0; x < this.board[0].length; x++) {
			
				if (!this.board[x][x].equals(p)) {
					winner = false;
					break;
				} else if (!this.board[(this.board.length - x) - 1][(this.board.length - x) - 1].equals(p)) {
					winner = false;
					break;
				}
			
			if (winner) {
				System.out.println(p + " wins!");
				return true;
			}
			winner = true;
		}
		return false;
	}
	
}
