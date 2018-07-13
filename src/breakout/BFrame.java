package breakout;

import javax.swing.JDialog;
import javax.swing.JFrame;

/***********************************************************************
 * Breakout Frame class to create the frame for the game. Extends
 * JFrame.
 * 
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class BFrame extends JFrame {

	/**	Default serial ID. */
	private static final long serialVersionUID = 1L;

	/**********************************************************************
	 * Constructor for the class. Creates a new panel, adds the panel to
	 * the frame, sets the default close operation and size, then makes
	 * the frame visible.
	 *********************************************************************/
	public BFrame() {
		BPanel panel = new BPanel();
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setSize(805, 620);
		setVisible(true);
	}
	
	/*
	public static void main(String[] args) {
		new BFrame();
	}
	*/
}
