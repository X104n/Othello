package game;

import inf101.grid.Grid;
import inf101.grid.GridDirection;
import inf101.grid.IGrid;
import inf101.grid.Location;

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

	public boolean isFull() {
		for(Location loc : locations()) {
			if(get(loc)==null)
				return false;
		}
		return true;
	}
	
	
	public GameBoard copy() { 
		GameBoard board = new GameBoard(this.numRows(), this.numColumns());
		fillCopy(board);
		return board;
	}
}
