package game;

import inf101.grid.Location;

public abstract class Game {

	
	protected GameBoard board;
	protected Graphics graphics;
	protected PlayerList players;
	
	public Game(GameBoard board, Graphics graphics){
		this.board = board;
		this.graphics = graphics;
		players = new PlayerList();
	}

	public void run() {

		//game loop
		while(!gameOver()) {
			Player current = players.getNextPlayer();
			Location loc = current.getMove(this);
			if(canPlace(loc,current)) {
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

	void addPlayer(Player player) {
		players.add(player);
	}

	public GameBoard getGameBoard() {
		return board;
	}
	
	public abstract boolean canPlace(Location loc, Player p);
	
	public abstract boolean isWinner(Player p);

	public abstract boolean gameOver();

	public void displayBoard() {
		graphics.display(board);
	}

}
