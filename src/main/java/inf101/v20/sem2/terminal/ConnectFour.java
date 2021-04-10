package inf101.v20.sem2.terminal;

import game.Game;
import game.GameBoard;
import game.Graphics;
import game.Player;
import game.TerminalGraphics;
import game.UseGameBoard;
import inf101.grid.Location;

public class ConnectFour extends Game {

	ConnectFour(GameBoard board, Graphics graphics) {
		super(board, graphics);
	}

	public ConnectFour(Graphics graphics, Player p1, Player p2) {
		this(new GameBoard(3,3), graphics);
	}


	@Override
	public boolean isWinner(Player p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public Location drop(int col) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canPlace(Location loc, Player p) {
		// TODO Auto-generated method stub
		return false;
	}

}
