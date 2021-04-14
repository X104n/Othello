package inf101.sem2.game;

import inf101.grid.Grid;
import inf101.grid.GridDirection;
import inf101.grid.IGrid;
import inf101.grid.Location;
import inf101.sem2.player.Player;

public class GameBoard extends Grid<Player>{

	public GameBoard(int rows, int cols){
		super(rows,cols);
	}
	
	@Override
	public void set(Location loc, Player elem) {
		if(canPlace(loc))
			super.set(loc, elem);
		else
			System.err.println("Can not place at "+loc+".");
	}

	
	/**
	 * This method determines wether or not it is allowed to place on a given location.
	 * This method does not consider the rules of the game just wether the space on the gameBoard
	 * is available.
	 * 
	 * This implementation only allows for one piece in each square of the board.
	 * 
	 * @param loc
	 * @return
	 */
	public boolean canPlace(Location loc) {

		try {
			return get(loc)==null;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for(int row = 0; row<numRows(); row++) {
			//print row
			for(int col=0; col<numColumns(); col++) {
				Player p = get(new Location(row,col));
				if(p==null) {
					s+=' ';
				}
				else {
					s += p.getSymbol();
				}
				if(col < numColumns()-1) {
					s+='|';
				}
			}
			//print newline
			s+= "\n";

			//print horizontal separator
			if(row <numRows()-1) {
				for(int col=0; col<numColumns(); col++) {
					s+= '-';
					if(col<numColumns()-1) {
						s+= '+';
					}
				}
				s+= "\n";
			}
			
		}
		
		return s;
	}
	
	/**
	 * Count the maximum number of pieces in a row that the given player has.
	 * @param p
	 * @return
	 */
	public int countNumInRow(Player p) {
		
		int max = 0;
		for(Location loc : locations()) {
			for(GridDirection dir : GridDirection.EIGHT_DIRECTIONS) {
				max = Math.max(max, countNumInRow(p,loc,dir));
			}
		}
		
		return max;
		
	}

	private int countNumInRow(Player p, Location start, GridDirection dir) {
		int count =0;
		
		while(isOnGrid(start) && get(start)==p) {
			count++;
			start = start.getNeighbor(dir);
		}
		
		return count;
	}

	/**
	 * This method checks whether all locations of the board is occupied 
	 * or there are any empty places on the board.
	 * 
	 * @return
	 */
	public boolean isFull() {
		for(Location loc : locations()) {
			if(canPlace(loc))
				return false;
		}
		return true;
	}
	
	/**
	 * Makes a shallow copy of the board
	 */
	public GameBoard copy() { 
		GameBoard board = new GameBoard(this.numRows(), this.numColumns());
		fillCopy(board);
		return board;
	}
}
