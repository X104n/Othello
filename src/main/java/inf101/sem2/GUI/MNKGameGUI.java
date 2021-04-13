package inf101.sem2.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import inf101.grid.Location;
import inf101.sem2.game.Game;
import inf101.sem2.game.GameBoard;
import inf101.sem2.game.Graphics;
import inf101.sem2.player.Player;

/**
 * This combines a message field and a clickable grid in one JPanel
 * 
 * @author Anna Eilertsen - anna.eilertsen@uib.no 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 */
public class MNKGameGUI extends JPanel implements Graphics{

	private static final long serialVersionUID = 8755882090377973497L;

	// Fields 
	private GameBoard board; //the game logic
	private ClickableGrid clickablePanels; //clickable grid for user input
	private JLabel statusMessage; //field for displaying message to user

	public MNKGameGUI(GameBoard board, Iterable<Player> players) {
		this.board = board; 
		//game.setGui(this);
		statusMessage = new JLabel();
//		statusMessage.setText("Welcome to this game! " + game.getCurrentPlayer() + " begins.");
		clickablePanels = new ClickableGrid(board, players, MNKGameGUI.getColors());
		initialize();
	}

	/**
	 * Sets up the GUI.
	 */
	private void initialize() {
		setLayout(new BorderLayout());
		add("North", statusMessage);
		add("Center", clickablePanels.getPanel());
		setVisible(true);
		validate();
	}

	/**
	 * Displays a message above the clickable grid of GamePanels
	 * 
	 * @param message
	 */
	public void displayMessage(String message) {
		statusMessage.setText(message);
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

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100*board.numRows(), 100*board.numRows());
	}

	/**
	 * Sets the Color of all the GamePanels in the clickable grid to match the game
	 * Then calls the method updateUI in JPanel.
	 */
	@Override
	public void updateUI() {
		if(clickablePanels!=null)
			clickablePanels.updateGui(board);
		super.updateUI();
	}

	@Override
	public void display(GameBoard board) {
		for(Location loc : board.locations()) {
			
		}
		updateUI();
	}
	
	public Location getClick() {
		return clickablePanels.getLastClick();
	}
	
}