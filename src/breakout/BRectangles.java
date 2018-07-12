package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/***********************************************************************
 * Breakout Rectangles class to create the rectangles. Uses final width
 * and height values to set up the sizes of the rectangle, then paints
 * the rectangle in.
 * 
 * @author kennemat
 * @version Summer 2018
 **********************************************************************/
public class BRectangles {
	/* x coordinate of the rectangle. */
	private int x;
	
	/* y coordinate of the rectangle. */
	private int y;
	
	/* Color of the rectangle. Randomly calculated. */
	private Color bColor;
	
	/* Width of the rectangle. */
	private static final int width = 80;
	
	/* Height of the rectangle. */
	private static final int height = 20;
	
	/**********************************************************************
	 * Constructor for the BRectangles class. Sets the rectangles x, y, and
	 * color based off what row / column the piece fits in.
	 * 
	 * @param row The row of the rectangle.
	 * @param col The column of the rectangle.
	 * @param bColor The color of the rectangle.
	 *********************************************************************/
	public BRectangles(int row, int col, Color bColor) {
		x = width * row;
		y = height * col;
		this.bColor = bColor;
	}
	
	/**********************************************************************
	 * Overridden paint methods for the class. Sets the color of the
	 * object, then paints in the rectangle.
	 * 
	 * @param g The graphics field for this object.
	 *********************************************************************/
	public void paint(Graphics g) {
		g.setColor(bColor);
		g.fillRect(x, y, width, height);
	}
	
	/**********************************************************************
	 * Returns the bounds for the rectangle.
	 * 
	 * @return A Rectangle object with the x, y, width, and height of this
	 * object.
	 *********************************************************************/
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
