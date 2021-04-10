package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerListTest {

	@Test
	void test() {
		PlayerList players = new PlayerList();
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		players.add(p1);
		players.add(p2);
		assertEquals(p1, players.getNextPlayer());
		assertEquals(p2, players.getNextPlayer());
		assertEquals(p1, players.getNextPlayer());
		assertEquals(p2, players.getNextPlayer());
	}

}
