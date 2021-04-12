package inf101.sem2.GUI;

import java.util.ArrayList;

import inf101.sem2.game.Game;
import inf101.sem2.game.MiniMaxPlayer;
import inf101.sem2.game.Player;
import inf101.sem2.game.TicTacToe;

public class TestGUI {

	public static void main(String[] args) {
		Player me = new GuiPlayer('X', "MaVa");
		Player computer = new MiniMaxPlayer('O', 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(me);
		players.add(computer);
		GameGUI graphics = new GameGUI();
		Game game = new TicTacToe(graphics,players);
		graphics.setGame(new MNKGameGUI(game.getGameBoard(), players));
		game.run();
	}

}
