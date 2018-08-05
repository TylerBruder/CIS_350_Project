package mainmenu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/***********************************************************************
 * Main menu class to organize accessing each game. Simple setup currently.
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class MainMenu implements ActionListener {
	/** JFrame to hold the panel. */
	private JFrame frame;

	/** JPanel to hold JButtons. */
	private JPanel panel;

	/** JButton to access the breakout game. */
	private JButton breakoutButton;

	/** JButton to access the the snake game. */
	private JButton snakeButton;
	
	/** JButton to access the dino game. */
	private JButton dinoButton;
	
	/** JButton to exit the program. */
	private JButton exitButton;
	
	/** ImageIcon for the breakout game. */
	private ImageIcon breakoutIcon;
	
	/** ImageIcon for the snake game. */
	private ImageIcon snakeIcon;
	
	/** ImageIcon for the dino  game. */
	private ImageIcon dinoIcon;
	
	/** ImageIcon for the exit button. */
	private ImageIcon exitIcon;
	/**********************************************************************
	 * Default constructor. Instantiates frame, panel, and button objects.
	 * Adds action listener to buttons, then makes frame visible.
	 *
	 *********************************************************************/
	public MainMenu() {
		frame = new JFrame();
		panel = new JPanel();

		breakoutButton = new JButton();
		snakeButton = new JButton();
		dinoButton = new JButton();
		exitButton = new JButton();
		
		breakoutIcon = new ImageIcon("gameIcons/breakout.jpg");
		snakeIcon = new ImageIcon("gameIcons/snake.jpg");
		dinoIcon = new ImageIcon("gameIcons/dino.png");
		exitIcon = new ImageIcon("gameIcons/exit.png");

		breakoutButton.setIcon(breakoutIcon);
		snakeButton.setIcon(snakeIcon);
		dinoButton.setIcon(dinoIcon);
		exitButton.setIcon(exitIcon);
		
		breakoutButton.setPreferredSize(new Dimension(290, 100));
		snakeButton.setPreferredSize(new Dimension(290, 100));
		dinoButton.setPreferredSize(new Dimension(290, 100));
		exitButton.setPreferredSize(new Dimension(290, 100));

		breakoutButton.addActionListener(this);
		snakeButton.addActionListener(this);
		dinoButton.addActionListener(this);
		exitButton.addActionListener(this);

		panel.setLayout(new GridLayout(2, 2, 10, 10));
		panel.add(breakoutButton);
		panel.add(snakeButton);
		panel.add(dinoButton);
		panel.add(exitButton);
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(600, 255);
		frame.setVisible(true);
	}

	/**********************************************************************
	 * Overridden action performed method. Checks which button was pressed,
	 * and runs the respective game.
	 *********************************************************************/
	@Override
	public void actionPerformed(final ActionEvent e) {
		frame.setVisible(false);
		if (e.getSource() == breakoutButton) {
			new breakout.BFrame();
		} else if (e.getSource() == snakeButton) {
			new cis.gvsu.edu.SnakeFrame();
		} else if (e.getSource() == dinoButton) {
			new dinogame.DinoFrame();
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	/**********************************************************************
	 * Main method. Entry point for the program. Makes a new MainMenu 
	 * object.
	 * 
	 * @param args Args for main method.
	 *********************************************************************/
	public static void main(final String[] args) {
		new MainMenu();
	}

}
