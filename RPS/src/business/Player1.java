package business;

import ui.Console;

public class Player1 extends Player {

	private static Console in = new Console();

	public Player1() {
		super(in.getString("Enter your name: "));
	}
}
