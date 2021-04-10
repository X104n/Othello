package game;

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
