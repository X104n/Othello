package game;

import inf101.grid.Location;

public class UseGameBoard {

	public static void main(String[] args) {
		GameBoard board = new GameBoard(3,5);
		Player p = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');

		board.set(new Location(2, 0), p);
		board.set(new Location(1, 1), p);
		board.set(new Location(0, 2), p);

		board.set(new Location(0, 1), p2);
		board.set(new Location(0, 0), p2);
		board.set(new Location(1, 1), p2);
		board.set(new Location(0, 3), p2);
		board.set(new Location(0, 4), p2);
		board.set(new Location(1, 0), p2);
		

		System.out.println(board);
		
		System.out.println("Player "+p.getSymbol()+" has "+ board.countNumInRow(p)+" in a row.");
		System.out.println("Player "+p2.getSymbol()+" has "+ board.countNumInRow(p2)+" in a row.");
	}

}
