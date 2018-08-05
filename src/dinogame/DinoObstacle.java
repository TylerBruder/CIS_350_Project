package dinogame;

import java.awt.Graphics;

/***********************************************************************
 * DinoObstacle interface. Provides abstract methods so that obstacles
 * can be instantiated and managed from an array.
 * 
 * @author kennemat
 * @version Summer 2018.
 **********************************************************************/
public interface DinoObstacle {
	/** Decrements the x position by some value. */
	void decX();
	
	/** Returns the current x position of an object. 
	 * @return the x value.*/
	int getX();
	
	/** Returns the current y position of an object. 
	 * @return the y value.*/
	int getY();
	
	/** Returns the width of an object. 
	 * @return the width.*/
	int getWidth();
	
	/** Returns the height of an object. 
	 * @return the height.*/
	int getHeight();
	
	/** Paint the object. 
	 * @param g the graphics passed.*/
	void paint(Graphics g);
	
	/** Resets the x position of an object. */
	void resetX();
	
	/** Increments the speed of an object. */
	void incSpeed();
}
