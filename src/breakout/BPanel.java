package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/***********************************************************************
 * Breakout Panel class to create a panel to hold the BRectangles, BBall,
 * and BPlayer. Also has a timer to check the new coordinates of the ball
 * and player.
 * 
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class BPanel extends JPanel implements ActionListener, KeyListener {
	/**	Default serial ID. */
	private static final long serialVersionUID = 1L;

	/** Array of the BRectangles that the ball will try to hit. */
	private BRectangles[] blocks;
	
	/** Array of colors for respective BRectangle array. */
	private Color[] blockColors;
	
	/** The player object. */
	private BPlayer player;
	
	/** The ball object. */
	private BBall ball;
	
	/** Max number of blocks for the game. */
	private static final int MAXBLOCKS = 60;
	
	/** Float value for red colors. */
	private float re;
	
	/** Float value for green colors. */
	private float gr;
	
	/** Float value for blue colors. */
	private float bl;
	
	/** Random object to get random color values. */
	private Random rand;

	/**********************************************************************
	 * Constructor to create the Panel class. Initializes the arrays and 
	 * creates random color values for each rectangle. Then, creates the
	 * individual rectangle objects. Lastly, adds listeners and creates
	 * a timer.
	 *********************************************************************/
	public BPanel() {
		blocks = new BRectangles[MAXBLOCKS];
		blockColors = new Color[MAXBLOCKS];
		rand = new Random();
		
		for (int i = 0; i < MAXBLOCKS; i++) {
			re = rand.nextFloat();
			gr = rand.nextFloat();
			bl = rand.nextFloat();
			
			while (re < 0.25 && gr < 0.25 && bl < 0.25) {
				re = rand.nextFloat();
				gr = rand.nextFloat();
				bl = rand.nextFloat();
			}			
			
			blockColors[i] = new Color(re, gr, bl);
		}
		
		int count = 0;
		for (int i = 0; i < MAXBLOCKS / 6; i++) {
			for (int j = 0; j < MAXBLOCKS / 10; j++) {
				blocks[count] = 
				new BRectangles(i, j, blockColors[count]);
				count++;
			}
		}
		
		addKeyListener(this);
		setFocusable(true);
		Timer timer = new Timer(4, this);
		timer.start();
		player = new BPlayer();
		ball = new BBall();
				
		setBackground(Color.black);
	}
	
	/**********************************************************************
	 * Overridden paintComponent method. Checks if each rectangle is not
	 * null. If object isn't null, paints them. Then paints the ball
	 * and the player.
	 *********************************************************************/
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < MAXBLOCKS; i++) {
			if (blocks[i] != null) {
				blocks[i].paint(g);
			}
		}
		
		player.paint(g);
		ball.paint(g);
	}
	
	/**********************************************************************
	 * Calls the update method for both the player and the ball, and checks
	 * if any collisions happened. 
	 *********************************************************************/
	private void update() {
		player.update();
		ball.update();
		checkCollision();
	}
	
	/**********************************************************************
	 * Checks if any collisions happened with the intersect method.
	 *********************************************************************/
	private void checkCollision() {
		if (ball.getBounds().intersects(player.getBounds())) {
			ball.switchDirection();
		}
		
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] != null) {
				if (ball.getBounds()
						.intersects(blocks[i]
								.getBounds())) {
					ball.switchDirection();
					blocks[i] = null;
					break;
				}
			}
		}
		
		if (checkWinner()) {
			JOptionPane.showMessageDialog(
					null, "You win!", 
					null, JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
	
	/**********************************************************************
	 * Checks if all rectangles are gone.
	 * 
	 * @return True if won, false if not.
	 *********************************************************************/
	private boolean checkWinner() {
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	/**********************************************************************
	 * Called when a key is pressed. 
	 * 
	 * @param arg0 Key code that was pressed.
	 *********************************************************************/
	@Override
	public void keyPressed(final KeyEvent arg0) {
		player.pressed(arg0.getKeyCode());
		
	}

	/**********************************************************************
	 * Called when a key is released. 
	 * 
	 * @param arg0 Key code that was released.
	 *********************************************************************/
	@Override
	public void keyReleased(final KeyEvent arg0) {
		player.released(arg0.getKeyCode());
		
		
	}

	/**********************************************************************
	 * Called when a key is typed. 
	 * 
	 * @param arg0 Key code that was typed.
	 *********************************************************************/
	@Override
	public void keyTyped(final KeyEvent arg0) {
	}

	/**********************************************************************
	 * Called from the swing timer every designated set of milliseconds.
	 * Updates and repaints.
	 * 
	 * @Param arg0 The action event that occurred, if any.
	 *********************************************************************/
	@Override
	public void actionPerformed(final ActionEvent arg0) {
		update();
		repaint();
	}
}
