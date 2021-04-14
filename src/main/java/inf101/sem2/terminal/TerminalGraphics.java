package inf101.sem2.terminal;

import inf101.sem2.game.GameBoard;
import inf101.sem2.game.Graphics;

public class TerminalGraphics implements Graphics {

	@Override
	public void displayMessage(String string) {
		System.out.println(string);
	}

	@Override
	public void display(GameBoard board) {
		System.out.println(board.toString());

	}

}
