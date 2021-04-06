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
			}
			
			//print newline
			s+= "/n";
		}
		
		return s;
	}
}
