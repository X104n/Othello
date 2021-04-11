package game;

import inf101.grid.Location;
import inf101.v20.sem2.terminal.ConsolePlayer;

public class TicTacToe extends Game{

	private TicTacToe(Graphics graphics) {
		super(new GameBoard(3,3), graphics);
	}

	public TicTacToe(Graphics graphics, Player player1, Player player2) {
		super(new GameBoard(3,3), graphics);
		addPlayer(player1);
		addPlayer(player2);
	}


	@Override
	public boolean gameOver() {
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
		Player player1 = new ConsolePlayer('X');
		Player player2 = new MiniMaxPlayer('O',4);
		Game game = new TicTacToe(new TerminalGraphics(), player1, player2 );
		game.run();
	}

	@Override
	public boolean canPlace(GameBoard board, Location loc, Player p) {
		return board.canPlace(loc);
	}


	protected TicTacToe copy() {
		TicTacToe game = new TicTacToe(graphics);
		copyTo(game);
		return game;
	}


	@Override
	public boolean isWinner(Player p) {
		return board.countNumInRow(p)>=3;
	}
}
