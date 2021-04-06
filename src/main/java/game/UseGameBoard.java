package game;

import inf101.grid.Location;

public class UseGameBoard {

	public static void main(String[] args) {
		GameBoard board = new GameBoard(3,3);
		Player p = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');

		board.set(new Location(2, 0), p);
		board.set(new Location(1, 1), p);
		board.set(new Location(0, 2), p);

		board.set(new Location(0, 1), p2);
		board.set(new Location(0, 0), p2);
		board.set(new Location(1, 1), p2);
		

		System.out.println(board);
	}

}
