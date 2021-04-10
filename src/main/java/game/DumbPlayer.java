package game;

import inf101.grid.Location;

public class DumbPlayer extends AbstractPlayer {

	static int counter=1;
	
	public DumbPlayer(char symbol) {
		super(symbol, "DumbPlayer "+counter++);
	}
	
	@Override
	public Location getMove(Game game) {
		GameBoard board = game.getGameBoard();
		for(Location loc : board.locations()) {
			if(board.canPlace(loc))
				return loc;
		}
		return null;
	}

}
