package inf101.sem2.GUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inf101.sem2.game.ConnectFour;
import inf101.sem2.game.Game;
import inf101.sem2.game.MiniMaxPlayer;
import inf101.sem2.game.Player;
import inf101.sem2.game.TicTacToe;

/**
 * This class is a Game menu which lets you choose which game to play.
 * 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 *
 */
public class MainMenu implements ActionListener{

	private JButton playConnectFourButton; //Button to start new 4 in row game
	private JButton playTicTacToeButton; //Button to start new TicTacToe game
	private JFrame frame;
	private JFrame gameBoard;
	public Game game;

	public MainMenu() {
		//make new main window for the game
		frame = new JFrame(); 
		frame.setTitle("Game menu");

		//make panel for game buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		
		//add one button for each game
		playTicTacToeButton = addButton(buttons, "Tic-Tac-Toe");
		playConnectFourButton = addButton(buttons, "Connect Four");

		//add buttons to the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(buttons);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setPreferredSize(new Dimension(1000, 1000));
		frame.setVisible(true);
	}

	
	JButton addButton(JPanel buttons, String name){
		JButton button = new JButton();
		button.setText(name);
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		button.addActionListener(this);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		//button.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		button.setBorder(new RoundedBorder(20)); //10 is the radius
		buttons.add(Box.createRigidArea(new Dimension(20, 20)));
		buttons.add(button);
		return button;
	}
	
	//this method is inherited from ActionListener and is the method
	//that gets called when buttons are clicked.
	@Override
	public void actionPerformed(ActionEvent e) {
		Iterable<Player> players = getPlayers();
		GameGUI graphics = new GameGUI();
		
		if(e.getSource() == playConnectFourButton) {
			game = new ConnectFour(graphics,players);
		}
		if(e.getSource() == playTicTacToeButton) {
			game = new TicTacToe(graphics,players);
		}
		
		graphics.setGame(new MNKGameGUI(game.getGameBoard(),players));
	}

	/**
	 * Generates a list of players based on user input
	 * 
	 * @return an Iterable of 2 Players
	 */
	public static Iterable<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		//add player1
		players.add(new GuiPlayer('X'));
		//add player2
		if(promptMultiplayer()) {
			players.add(new GuiPlayer('O'));
		}else {
			//make AI
			//TODO: prompt for level of intelligence in AI player
			players.add(new MiniMaxPlayer('O', 5));
		}
		
		
		return players;
	}
	
	/**
	 * Helper method that prompts for multiplayer or not  
	 * @return true if multiplayer is selected, false otherwise
	 */
	private static boolean promptMultiplayer() {
		Object[] possibilities = {"Multiplayer", "Single Player (against AI)"};
		String s = (String)JOptionPane.showInputDialog(
				null,
				"Welcome:\n"
						+ "Select one or two players",
						"MKGame StartUp",
						JOptionPane.PLAIN_MESSAGE,
						null,
						possibilities,
						null);

		//If a string was returned, say so.
		if ((s != null) && (s.length() > 0)) {
			System.out.println("Received " + s);
		}
		return s.equals(possibilities[0]);
	}
}


