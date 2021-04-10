package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	public static void testConstructor(String symbol, String name, Player p) {
		assertEquals(name, p.toString());
		assertEquals(symbol, p.getSymbol());
	}

	@Test
	void testisValidName() {
		assertTrue(AbstractPlayer.isValidName("Martin"));
		assertFalse(AbstractPlayer.isValidName(" "));
		assertFalse(AbstractPlayer.isValidName("\n"));
		assertFalse(AbstractPlayer.isValidName("\t"));
	}
	
	@Test
	void testValidateName() {
		testValidName("Martin");
		try {
			AbstractPlayer.validateName("");
		} catch (IllegalArgumentException e) {
			return;
		} catch(Exception e) {
			fail("Should throw an IllegalArgumentException");
		}
	}

	private void testValidName(String name) {
		try {
			assertEquals(name, AbstractPlayer.validateName(name));
		} catch (Exception e) {
			fail(name+" is not a vailld name.");
		}
	}

	@Test
	void testConstruct() {
		testConstruct(new DumbPlayer('X'));
	}

	private void testConstruct(Player player) {
		testValidName(player.toString());
		
	}

}
