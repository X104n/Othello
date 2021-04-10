package game;

public class TicTacToe extends Game{

	TicTacToe(Graphics graphics) {
		super(new GameBoard(3,3), graphics);
		addPlayer('X');
		addPlayer('O');
	}

	@Override
	protected boolean isWinner(Player p) {
		return board.countNumInRow(p)>=3;
	}

	@Override
	boolean gameOver() {
		for(Player p : players) {
			if(isWinner(p)) {
				return true;
			}
		}
		
		if(board.isFull()) {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) {
		Game game = new TicTacToe(new TerminalGraphics());
		game.run();
	}
}
