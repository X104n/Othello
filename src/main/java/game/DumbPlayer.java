package game;

import inf101.grid.Location;

public class DumbPlayer implements Player {

	char symbol;
	
	public DumbPlayer(char symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public char getSymbol() {
		return symbol;
	}

	@Override
	public Location getMove() {
		return new Location(0,0);
	}

}
