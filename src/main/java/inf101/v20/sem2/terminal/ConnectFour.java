package inf101.v20.sem2.terminal;

import game.DumbPlayer;
import game.Game;
import game.GameBoard;
import game.Graphics;
import game.Player;
import game.TerminalGraphics;
import game.TicTacToe;
import game.UseGameBoard;
import inf101.grid.GridDirection;
import inf101.grid.Location;

public class ConnectFour extends Game {

	ConnectFour(GameBoard board, Graphics graphics) {
		super(board, graphics);
	}

	public ConnectFour(Graphics graphics, Player p1, Player p2) {
		this(new GameBoard(6,7), graphics);
		players.add(p1);
		players.add(p2);
	}


	@Override
	public boolean isWinner(Player p) {
		return board.countNumInRow(p)>=4;
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

	public Location drop(int col) {
		Location loc = new Location(0,col);
		
		while(board.canPlace(loc) && !canPlace(loc)) {
			loc = loc.getNeighbor(GridDirection.SOUTH);
		}
		return loc;
	}

	@Override
	public boolean canPlace(Location loc, Player p) {
		return canPlace(loc);
	}
	public boolean canPlace(Location loc) {
		return board.canPlace(loc) && !board.canPlace(loc.getNeighbor(GridDirection.SOUTH));
	}

	public static void main(String[] args) {
		Player player1 = new ConsolePlayer('X');
		Player player2 = new DumbPlayer('O');
		Game game = new ConnectFour(new TerminalGraphics(), player1, player2);
		game.run();
	}

}
