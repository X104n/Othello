package inf101.sem2.game;

import inf101.grid.Location;
import inf101.sem2.GUI.GameGUI;
import inf101.sem2.player.ConsolePlayer;
import inf101.sem2.player.MiniMaxPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;

public class TicTacToe extends Game{

	public TicTacToe(Graphics graphics) {
		super(new GameBoard(3,3), graphics);
	}

	public TicTacToe(Graphics graphics, Player player1, Player player2) {
		super(new GameBoard(3,3), graphics);
		addPlayer(player1);
		addPlayer(player2);
	}


	public TicTacToe(Graphics graphics, Iterable<Player> players) {
		super(new GameBoard(3,3), graphics,players);
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


	public TicTacToe copy() {
		TicTacToe game = new TicTacToe(graphics);
		copyTo(game);
		return game;
	}


	@Override
	public boolean isWinner(Player p) {
		return board.countNumInRow(p)>=3;
	}

	@Override
	public String getName() {
		return "Tic Tac Toe";
	}
}
