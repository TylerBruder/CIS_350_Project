package dinogame;

import java.awt.Graphics;

/***********************************************************************
 * DinoGround class. This is just a line across the frame to indicate 
 * the ground.
 * 
 * @author kennemat
 * @version Summer 2018.
 **********************************************************************/
public class DinoGround {
	
	/**********************************************************************
	 * Paint method for this class. Draws the ground across the frame at a
	 * given y.
	 * 
	 * @param g The graphics variable for the paint method.
	 * @param maxX The "length" of the ground across the frame.
	 * @param yPos The y position of the ground.
	 *********************************************************************/
	public void paint(final Graphics g, final int maxX, final int yPos) {
		g.drawLine(0, yPos, maxX, yPos);
	}

}
