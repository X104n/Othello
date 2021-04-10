package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
