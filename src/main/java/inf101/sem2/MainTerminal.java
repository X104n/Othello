package inf101.sem2;

import java.util.ArrayList;

import inf101.sem2.game.Game;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalMenu;

public class MainTerminal {

	public static void main(String[] args) {
		ArrayList<Player> players = TerminalMenu.getPlayers();
		boolean done = false;
		while(!done) {
			Game game = TerminalMenu.selectGame(players);
			game.run();
			done = TerminalMenu.isDone();

		}
	}


}
