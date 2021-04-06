package game;

import inf101.grid.Grid;
import inf101.grid.Location;

public class GameBoard extends Grid<Player>{

	GameBoard(int rows, int cols){
		super(rows,cols);
	}
	
	@Override
	public void set(Location loc, Player elem) {
		if(canPlace(loc))
			super.set(loc, elem);
		else
			System.err.println("Can not place at "+loc+".");
	}

	private boolean canPlace(Location loc) {
		return get(loc)==null;
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
}
