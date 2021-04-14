package inf101.sem2.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import inf101.grid.Location;
import inf101.sem2.game.GameBoard;
import inf101.sem2.player.Player;

/**
 * This combines a message field and a clickable grid in one JPanel
 * 
 * @author Anna Eilertsen - anna.eilertsen@uib.no 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 */
public class MNKGameGUI extends JPanel{

	private static final long serialVersionUID = 8755882090377973497L;

	// Fields 
	private ClickableGrid clickablePanels; //clickable grid for user input

	public MNKGameGUI() {
		initialize();
	}

	/**
	 * Sets up the GUI.
	 */
	private void initialize() {
		setLayout(new BorderLayout());
		add("Center", clickablePanels);
		setVisible(true);
		validate();
	}
	
	public void display(GameBoard board) {
		if(clickablePanels!=null)
			clickablePanels.updateGui();
		updateUI();
	}
	
	public Location getClick() {
		return clickablePanels.getLastClick();
	}	
}