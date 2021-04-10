package game;

import inf101.grid.Location;

public abstract class Game {

	
	protected GameBoard board;
	protected Graphics graphics;
	protected PlayerList players;
	
	Game(GameBoard board, Graphics graphics){
		this.board = board;
		this.graphics = graphics;
		players = new PlayerList();
	}

	public void run() {
		
		graphics.display(board);

		//game loop
		while(!gameOver()) {
			Player current = players.getNextPlayer();
			graphics.display(board);
			Location loc = current.getMove(board);
			if(board.canPlace(loc)) {
				board.set(loc,current);
				graphics.display(board);
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

	void addPlayer(char symbol) {
		players.add(new DumbPlayer(symbol));
	}

	protected abstract boolean isWinner(Player p);

	abstract boolean gameOver();

}
