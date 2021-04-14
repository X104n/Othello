package inf101.sem2.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inf101.grid.Location;
import inf101.sem2.game.GameBoard;
import inf101.sem2.game.Graphics;
import inf101.sem2.player.Player;

/**
 * This class combines two buttons with a MNKGameGUI in one JFrame
 * The buttons are used to select type of game to play.
 *  
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 *
 */
public class GameGUI implements ActionListener, Graphics{

	private JButton backButton; //Button to go back to end the game and go back to main menu
	private JButton restartButton; //Button to restart the game
	private JLabel statusMessage; //field for displaying message to user
	private ClickableGrid clickablePanels; //clickable grid for user input
	private Iterable<Player> players;
	private JFrame frame;
	
	public boolean wantRestart=false;
	public boolean ended = false;
	
	public GameGUI(Iterable<Player> players) {
		this.players = players;
		JPanel buttons = createButtonPanel();
		statusMessage = new JLabel();
		statusMessage.setPreferredSize(new Dimension(300,50));
		statusMessage.setText("Welcome!");		

		//make new main window for the game
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(statusMessage, BorderLayout.NORTH);
		frame.add(buttons, BorderLayout.SOUTH);
		drawGameBoard();
	}

	public void setName(String name){
		frame.setTitle(name);
	}

	/**
	 * Draws the gameBoard on the JFrame 
	 * and refreshes the rest of the graphics
	 */
	private void drawGameBoard() {
		//add the clickable grid panel
		frame.doLayout();
		frame.pack();
		frame.setVisible(true);
		frame.validate();
	}

	/**
	 * Helper method that creates the button panel 
	 */
	private JPanel createButtonPanel() {
		backButton = new JButton();
		backButton.addActionListener(this);
		backButton.setText("Back");

		restartButton = new JButton();
		restartButton.addActionListener(this);
		restartButton.setText("Restart");
		
		JPanel buttons = new JPanel();
		//buttons.setLayout(new BorderLayout());
		//buttons.add(backButton, BorderLayout.WEST);
		//buttons.add(restartButton, BorderLayout.EAST);
		buttons.add(backButton);
		buttons.add(restartButton);
		return buttons;
	}


	// Called whenever a button is pressed  
	@Override
	public void actionPerformed(ActionEvent e) {
		//removeClickablePanels();
		if(e.getSource() == backButton) {
			ended = true;
			System.err.println("GameGui has ended game");
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			return;
		}
		if(e.getSource() == restartButton) {
			wantRestart = true;
			return;
		}
		
		drawGameBoard();
	}

	public void removeClickablePanels() {
		if(clickablePanels!=null && clickablePanels.getParent()!=null)
			clickablePanels.getParent().remove(clickablePanels);
	}

	@Override
	public void displayMessage(String message) {
		statusMessage.setText(message);		
	}

	@Override
	public void display(GameBoard board) {
		removeClickablePanels();
		clickablePanels = new ClickableGrid(board, players, getColors());
		frame.add("Center", clickablePanels);
		frame.setMinimumSize(new Dimension(100*board.numColumns(), 100*board.numRows()+100));
		drawGameBoard();
	}

	public Location getMove() {
		return clickablePanels.getLastClick();
	}

	/**
	 * Maps from Piece values to colors 
	 * 
	 * @param pieceAt The piece to be drawn 
	 * @return The color that this GUI implementation associates with the provided piece
	 */
	protected static List<Color> getColors() {
		Color[] colors = {Color.BLUE,Color.RED};
		return Arrays.asList(colors);
	}
}
