package inf101.sem2.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import inf101.grid.Location;
import inf101.sem2.game.ConnectFour;
import inf101.sem2.game.Game;
import inf101.sem2.game.GameBoard;
import inf101.sem2.game.Graphics;
import inf101.sem2.game.TicTacToe;
import inf101.sem2.player.MiniMaxPlayer;
import inf101.sem2.player.Player;


/**
 * This class combines two buttons with a MNKGameGUI in one JFrame
 * The buttons are used to select type of game to play.
 *  
 * @author Anna Eilertsen - anna.eilertsen@uib.no 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 *
 */
public class GameGUI implements ActionListener, Graphics{

	private JButton backButton; //Button to go back to end the game and go back to main menu
	private JButton restartButton; //Button to restart the game
	private JFrame frame;
	MNKGameGUI gameBoard;

	public GameGUI() {
		JPanel buttons = createButtonPanel();
		
		//make new main window for the game
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(buttons, BorderLayout.SOUTH);
	}
	
	/**
	 * Draws the gameBoard on the JFrame 
	 * and refreshes the rest of the graphics
	 */
	private void drawGameBoard() {
		//add the clickable grid panel
		frame.add("Center", gameBoard);
		frame.pack();
		frame.setVisible(true);
		//update to reflect the state of the game
		gameBoard.updateUI();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Called whenever a button is pressed  
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.remove(gameBoard);
		if(e.getSource() == backButton) {
			//TODO
		}
		if(e.getSource() == restartButton) {
			//TODO
		}
		
		drawGameBoard();
	}

	@Override
	public void displayMessage(String string) {
		gameBoard.displayMessage(string);		
	}

	@Override
	public void display(GameBoard board) {
		gameBoard.display(board);
	}

	public Location getMove() {
		return gameBoard.getClick();
	}

	public void setGame(MNKGameGUI gui) {
		gameBoard = gui;
		drawGameBoard();
	}
}
