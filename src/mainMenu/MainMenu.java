package mainMenu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/***********************************************************************
 * Main menu class to organize accessing each game. Simple setup 
 * currently.
 * 
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class MainMenu implements ActionListener {
	/* JFrame to hold the panel. */
	JFrame frame;
	
	/* JPanel to hold JButtons. */
	JPanel panel;
	
	/* JButton to access the breakout game. */
	JButton breakoutButton;
	
	/* JButton to access the the snake game. */
	JButton snakeButton;
	
	/**********************************************************************
	 * Default constructor. Instantiates frame, panel, and button objects.
	 * Adds action listener to buttons, then makes frame visible.
	 *
	 *********************************************************************/
	public MainMenu()
	{
		frame = new JFrame();
		panel = new JPanel();
		
		breakoutButton = new JButton("Breakout");
		snakeButton = new JButton("Snake");
		
		breakoutButton.setPreferredSize(new Dimension(100, 30));
		snakeButton.setPreferredSize(new Dimension(100, 30));
		
		breakoutButton.addActionListener(this);
		snakeButton.addActionListener(this);
		
		panel.add(breakoutButton);
		panel.add(snakeButton);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		frame.setSize(200, 120);
		frame.setVisible(true);
	}
	
	/**********************************************************************
	 * Overridden action performed method. Checks which button was
	 * pressed, and runs the respective game.
	 *
	 *@param ActionEvent e The action event that occurred.
	 *********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == breakoutButton)
			frame = new breakout.BFrame();
		else if (e.getSource() == snakeButton)
		{
			frame.setVisible(false);
			new cis.gvsu.edu.Main();
		}
	}
	
	/**********************************************************************
	 * Main method. Entry point for the program. Makes a new MainMenu
	 * object.
	 *********************************************************************/
	public static void main(String[] args)
	{
		new MainMenu();
	}
	
}
