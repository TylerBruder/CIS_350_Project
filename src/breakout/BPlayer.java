package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***********************************************************************
 * Breakout Player class to create the player rectangle for the game.
 * Movable rectangle with the left and right arrow keys.
 * 
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class BPlayer {
	/** Current x of the player rectangle. */
	private int x;
	
	/** The difference in the x at which to paint the player next. */
	private int xDiff;
	
	/** Current y of the player rectangle. */
	private int y;
	
	/** Key code for the left arrow key. */
	private final int left = 37;
	
	/** Key code for the right arrow key. */
	private final int right = 39;
	
	/** Width of the game window. */
	private static final int PWIDTH = 805;
	
	/** Height of the game window. */
	private static final int PHEIGHT = 600;
	
	/** Width of the player rectangle. */
	private static final int WIDTH = 120;
	
	/** Height of the player rectangle. */
	private static final int HEIGHT = 10;
	
	/**********************************************************************
	 * Constructor to create the player rectangle. Sets the x, y, and xDiff
	 * values.
	 *********************************************************************/
	public BPlayer() {
		x = PWIDTH / 2;
		y = PHEIGHT - 50;
		xDiff = 0;
	}
	
	/**********************************************************************
	 * Updates the current player position. If the x value is within the
	 * accepted parameters, then updates the position. If it would move
	 * off the screen, then it does not move.
	 *********************************************************************/
	public void update() {
        if (x > 10 && x < PWIDTH - WIDTH - 20) {
			x += xDiff;
		} else if (x <= 20) {
			x++;
		} else if (x >= PWIDTH - WIDTH - 20) {
			x--;
		}
    }
	
	/**********************************************************************
	 * Called when the left or right arrow key is pressed. If the left
	 * arrow key is pressed, then sets the difference to -1. If the right
	 * arrow key is pressed, sets the difference to 1.
	 * 
	 * @param key The key which was pressed.
	 *********************************************************************/
	public void pressed(final int key) {
		if (key == left) {
			xDiff = -1;
		} else if (key == right) {
			xDiff = 1;
		}
	}
	
	/**********************************************************************
	 * Called when the left or right arrow key is released. Sets the
	 * difference to 0 if the left or right arrow key was released.
	 * 
	 * @param key The key which was released.
	 *********************************************************************/
	public void released(final int key) {
        if (key == left || key == right) {
			xDiff = 0;
		}
    }
	
	/**********************************************************************
	 * Returns the bounds of the player rectangle.
	 * 
	 * @return A Rectangle object that has the x, y, width, and height of
	 * the player rectangle.
	 *********************************************************************/
	public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
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
