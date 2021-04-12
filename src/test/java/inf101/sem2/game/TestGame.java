package inf101.sem2.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import inf101.sem2.game.DumbPlayer;
import inf101.sem2.game.Game;
import inf101.sem2.game.Player;
import inf101.sem2.game.TicTacToe;
import inf101.sem2.terminal.TerminalGraphics;

class TestGame {

	@Test
	void testDumbPlayerCanPlay() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game game = new TicTacToe(new TerminalGraphics(),p1,p2);
		game.run();
		assertTrue(game.gameOver());
	}

}
