package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JOptionPane;

/***********************************************************************
 * Breakout blal class to create the ball. Ball will continuously move
 * around the game.
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class BBall {
	/** Width of the game. */
	private static final int PWIDTH = 810;

	/** Height of the game. */
	private static final int PHEIGHT = 600;

	/** Width of the ball. */
	private static final int WIDTH = 15;
	
	/** Height of the ball. */
	private static final int HEIGHT = 15;
	
	/** x position of the ball. */
	private int x;
	
	/** y position of the ball. */
	private int y;
	
	/** Difference in x the ball moved since last update. */
	private int xDiff;
	
	/** Difference in y the ball moved since last update. */
	private int yDiff;
	
	/**********************************************************************
	 * Constructor to create the random number generator, to give the ball
	 * a random start, then assigns the ball to move down and to the right.
	 *********************************************************************/
	public BBall() {
		Random rand = new Random();
		x = rand.nextInt(PWIDTH - 50) + 50;
		y = PHEIGHT / 2 - 150;
	
		xDiff = 1;
		yDiff = 1;
	}
	
	/**********************************************************************
	 * Update method to check where the ball is. If any collisions with 
	 * walls happened, the update method will check what direction the ball
	 * should be moving.
	 *********************************************************************/
	public void update() {
		x += xDiff;
		y += yDiff;
		
        if (y > PHEIGHT - HEIGHT - 20) {
        	JOptionPane.showMessageDialog(
        			null, "You lose.",
        			null, JOptionPane.PLAIN_MESSAGE);
        	System.exit(0);
        } else if (y == 0) {
        	yDiff = -yDiff;
        } else if (x <= 0 || x >= PWIDTH - WIDTH - 14) {
			xDiff = -xDiff;
		}
	}
	
	/**********************************************************************
	 * Returns the bounds of the ball rectangle.
	 * 
	 * @return A Rectangle object that has the x, y, width, and height of
	 * the ball rectangle.
	 *********************************************************************/
	public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
	
	/**********************************************************************
	 * Switches the y direction of the ball.
	 * 
	 *********************************************************************/
	public void switchDirection() {
		yDiff = -yDiff;
	}
	
	/**********************************************************************
	 * Overridden paint methods for the class. Sets the color of the
	 * object, then paints in the rectangle.
	 * 
	 * @param g The graphics field for this object.
	 *********************************************************************/
	public void paint(final Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}
