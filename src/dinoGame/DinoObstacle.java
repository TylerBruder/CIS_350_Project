package dinoGame;

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
	public abstract void decX();
	
	/** Returns the current x position of an object. */
	public abstract int getX();
	
	/** Returns the current y position of an object. */
	public abstract int getY();
	
	/** Returns the width of an object. */
	public abstract int getWidth();
	
	/** Returns the height of an object. */
	public abstract int getHeight();
	
	/** Paint the object. */
	public abstract void paint(final Graphics g);
	
	/** Resets the x position of an object. */
	public abstract void resetX();
	
	/** Increments the speed of an object. */
	public abstract void incSpeed();
}
