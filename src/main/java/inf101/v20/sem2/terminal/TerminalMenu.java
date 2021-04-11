package inf101.v20.sem2.terminal;

import java.util.ArrayList;
import java.util.Scanner;

import game.ConnectFour;
import game.DumbPlayer;
import game.Game;
import game.Player;
import game.TerminalGraphics;
import game.TicTacToe;

public class TerminalMenu {

	public TerminalMenu() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		System.out.println("Player 1, what is your name?");
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new ConsolePlayer('X'));
		System.out.println("(1) Two players or \n(2) play against computer?");
		int multiplayerChoice = TerminalInput.readInt(new Scanner(System.in));
		switch (multiplayerChoice) {
		case 1:
			players.add(new ConsolePlayer('O'));
			break;
		case 2:
			//players.add(new MiniMaxPlayer('O', 5));
			players.add(new DumbPlayer('O'));
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + multiplayerChoice);
		}
		
		System.out.println("Which game do you wish to play?");
		System.out.println("Press 1 for TicTacToe and 2 for Connect 4");
		int choice = TerminalInput.readInt(new Scanner(System.in));
		Game game;
		switch (choice) {
		case 1:
			game = new TicTacToe(new TerminalGraphics(),players.get(0),players.get(1));
			break;

		case 2:
			game = new ConnectFour(new TerminalGraphics(),players.get(0),players.get(1));
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + choice);
		}
		game.run();
	}
}
