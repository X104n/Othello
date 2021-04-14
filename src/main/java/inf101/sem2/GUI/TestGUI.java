package inf101.sem2.GUI;

import java.util.ArrayList;

import inf101.sem2.game.Game;
import inf101.sem2.game.TicTacToe;
import inf101.sem2.player.GuiPlayer;
import inf101.sem2.player.MiniMaxPlayer;
import inf101.sem2.player.Player;

public class TestGUI {

	public static void main(String[] args) {
		Player me = new GuiPlayer('X', "MaVa");
		Player computer = new MiniMaxPlayer('O', 4);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(me);
		players.add(computer);
		GameGUI graphics = new GameGUI(players);
		Game game = new TicTacToe(graphics,players);
		graphics.setName("Tic Tac Toe");
		graphics.display(game.getGameBoard());
		game.run();
	}

}
