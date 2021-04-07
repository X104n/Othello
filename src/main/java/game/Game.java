package game;

import inf101.grid.Location;

public abstract class Game {

	
	private GameBoard board;
	private Graphics graphics;
	private PlayerList players;

	public void run() {
		
		//game loop
		while(!gameOver()) {
			Player current = players.getNextPlayer();
			Location loc = current.getMove();
			if(board.canPlace(loc)) {
				board.set(loc,current);
			}
			else {
				System.err.println("Invalid move by player "+current+" lost turn.");
			}
		}
		
		//print results when game is over
		graphics.displayMessage("Game is over!");
		
		for(Player p : players) {
			if(isWinner(p)) {
				graphics.displayMessage("Player "+p+" has won!");
			}
		}
		
	}

	protected abstract boolean isWinner(Player p);

	protected abstract Player getCurrentPlayer();

	abstract boolean gameOver();

}
