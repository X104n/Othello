package game;

import java.util.ArrayList;
import java.util.List;

import inf101.grid.Location;

/**
 * This class models turn based games where each round the current player gets to place one piece.
 * Games of this sort has win/tie/loose conditions and rules for where it is possible to place pieces.
 * 
 * This type of games does not allow players to move pieces unless
 * 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 *
 */
public abstract class Game {

	//keeps track of where players have placed their pieces
	protected GameBoard board;
	
	//displays messages and current state of the game
	protected Graphics graphics;
	
	//keeps track of whose turn it is
	protected PlayerList players;
	
	/*************** Constructors ****************/
	public Game(GameBoard board, Graphics graphics){
		this.board = board;
		this.graphics = graphics;
		players = new PlayerList();
	}
	
	public Game(GameBoard board, Graphics graphics, Iterable<Player> players){
		this(board,graphics);
		for(Player p : players) {
			addPlayer(p);
		}
	}

	/**
	 * This is the main game loop making sure each player gets to place one piece each turn.
	 */
	public void run() {

		//game loop
		while(!gameOver()) {
			Location loc = getCurrentPlayer().getMove(copy());
			if(canPlace(loc)) {
				makeMove(loc);
			}
			else {
				System.err.println("Invalid move by player "+getCurrentPlayer()+" lost turn.");
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

	/**
	 * When players are asked to make a move we don't want them to change the
	 * state of the game so we send them a copy of the game.
	 */
	protected abstract Game copy();

	public void copyTo(Game target) {
		target.board = board.copy();
		target.graphics = graphics;
		target.players = players.copy();
	}

	/**
	 * This method performs a move for the current player and advances to next player
	 * @param loc
	 */
	public void makeMove(Location loc) {
		if(!canPlace(loc, getCurrentPlayer()))
			throw new IllegalArgumentException("Can not make that move");
		
		board.set(loc, getCurrentPlayer());
		players.nextPlayer();
	}

	void addPlayer(Player player) {
		players.add(player);
	}

	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * The game has rules for where the players can place.
	 * In both TicTacToe and Connect4 it does not matter who the player is, but it might in other games.
	 * 
	 * This is both used to verify that the move a Player returns is valid
	 * and for the AI to know where it can place.
	 * 
	 * @param loc - where to place
	 * @param player - who wants to place
	 * @return true if it is a valid move, false otherwise.
	 */
	public boolean canPlace(Location loc, Player p) {
		return canPlace(board, loc, p);
	}
	public boolean canPlace(Location loc) {
		return canPlace(board,loc,getCurrentPlayer());
	}
	public abstract boolean canPlace(GameBoard board, Location loc, Player p);
	
	public abstract boolean isWinner(Player player);

	public boolean isLooser(Player player) {
		for(Player p : players()) {
			if(p != player && isWinner(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Computes a score for the player p in the current game
	 * to be used when choosing the best move.
	 * @param game
	 * @param p
	 * @return
	 */
	public int score(Player p) {
		if(isWinner(p)) {
			return 1;
		}
		if(isLooser(p)) {
			return -1;
		}
		return 0;
	}
	
	public abstract boolean gameOver();

	public void displayBoard() {
		graphics.display(board);
	}

	public Iterable<Player> players() {
		return players.copy();
	}

	public Player getCurrentPlayer() {
		return players.getCurrentPlayer();
	}

	public List<Location> getPossibleMoves() {
		ArrayList<Location> moves = new ArrayList<Location>();
		for(Location loc : board.locations()) {
			if(canPlace(board,loc, getCurrentPlayer())) {
				moves.add(loc);
			}
		}
		return moves;
	}
}
