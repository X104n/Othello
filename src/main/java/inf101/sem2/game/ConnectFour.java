package inf101.sem2.game;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.ConsolePlayer;
import inf101.sem2.player.MiniMaxPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;

public class ConnectFour extends Game {

	public ConnectFour(Graphics graphics, Player p1, Player p2) {
		this(graphics);
		players.add(p1);
		players.add(p2);
	}

	public ConnectFour(Graphics graphics) {
		super(new GameBoard(6, 7), graphics);
	}

	public ConnectFour(Graphics graphics, Iterable<Player> players) {
		super(new GameBoard(6, 7), graphics, players);
	}

	@Override
	public boolean isWinner(Player p) {
		return board.countNumInRow(p) >= 4;
	}

	@Override
	public boolean gameOver() {
		for(Player p : players) {
			if(isWinner(p)) {
				return true;
			}
		}

		return board.isFull();
	}

	@Override
	public void makeMove(Location loc) {
		super.makeMove(drop(loc));
	}

	private Location drop(Location loc) {
		while(board.canPlace(below(loc))) {
			loc = below(loc);
		}
		return loc;
	}
	
	private Location below(Location loc) {
		return loc.getNeighbor(GridDirection.SOUTH);
	}
	
	public Location drop(int col) {
		return drop(new Location(0, col));
	}

	@Override
	public boolean canPlace(Location loc, Player p) {
		return canPlace(board, loc, p);
	}

	@Override
	public boolean canPlace(Location loc) {
		return canPlace(board, loc, getCurrentPlayer());
	}

	@Override
	public boolean canPlace(GameBoard board, Location loc, Player p) {
		return board.canPlace(loc) && !board.canPlace(loc.getNeighbor(GridDirection.SOUTH));
	}


	@Override
	public Game copy() {
		ConnectFour game = new ConnectFour(graphics);
		copyTo(game);
		return game;
	}

	public static void main(String[] args) {
		Player player1 = new ConsolePlayer('X');
		Player player2 = new MiniMaxPlayer('O', 4);
		Game game = new ConnectFour(new TerminalGraphics(), player1, player2);
		game.run();
	}

	@Override
	public String getName() {
		return "Connect Four";
	}

}
