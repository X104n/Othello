package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerListTest {

	@Test
	void testAlternatingPlayers() {
		PlayerList players = new PlayerList();
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		players.add(p1);
		players.add(p2);
		assertEquals(p1, players.getCurrentPlayer());
		assertEquals(p2, players.nextPlayer());
		assertEquals(p2, players.getCurrentPlayer());
		assertEquals(p1, players.nextPlayer());
		assertEquals(p1, players.getCurrentPlayer());
		assertEquals(p2, players.nextPlayer());
		assertEquals(p2, players.getCurrentPlayer());
	}

}
