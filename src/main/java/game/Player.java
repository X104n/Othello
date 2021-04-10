package game;

import inf101.grid.Location;

public interface Player {

	char getSymbol();

	Location getMove(GameBoard board);

}
