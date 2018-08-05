package dinogame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/***********************************************************************
 * DinoCactusBig for the big cactus obstacle. Implements the
 * DinoObstacle interface. Cactus moves at constant speed across the 
 * screen.
 * 
 * @author mkennedy
 * @version Summer 2018.
 **********************************************************************/
public class DinoCactusBig implements ImageObserver, DinoObstacle {
	
	/** Default x position for the obstacle. */
	private static final int DEFAULTX = 930;
	
	/** Default y position for the obstacle. */
	private static final int DEFAULTY = 457;
	
	/** Width of the obstacle sprite. */
	private static final int WIDTH = 42;
	
	/** Height of the obstacle sprite. */
	private static final int HEIGHT  = 83;
	
	/** Actual x position variable. */
	private int xPos;
	
	/** Actual y position variable. */
	private int yPos;
	
	/** Speed factor for how fast the obstacle is moving. */
	private int speedFactor = 0;

	/** BufferedImage variable for the obstacle sprite. */
	private BufferedImage bigCactusImg;
	
	/**********************************************************************
	 * Constructor for the DinoCactusBig class. Initializes variables.
	 *********************************************************************/
	public DinoCactusBig() {
		try {
			bigCactusImg = ImageIO.read(
					new File("sprites/cactusBig0000.png"));
		} catch (IOException e) {
			Object[] options = {"OK"};
	        JOptionPane.showOptionDialog(null,
	                "Error loading image files. ", "Error",
	                JOptionPane.PLAIN_MESSAGE,
	                JOptionPane.QUESTION_MESSAGE, 
	                null, options, options[0]);
		}
		
		xPos = DEFAULTX;
		yPos = DEFAULTY;
	}
	
	/**********************************************************************
	 * Paint method for this class. Draws the sprite at the given x
	 * position, y position, width, and height.
	 * 
	 * @param g The graphics variable for the paint method.
	 *********************************************************************/
	public void paint(final Graphics g) {
		g.drawImage(bigCactusImg, xPos, yPos, WIDTH, HEIGHT, this);
	}

	/**********************************************************************
	 * Inherited from ImageObserver. Does nothing here.
	 * 
	 * @param img The image updated.
	 * @param infoflags Information flags.
	 * @param x The x position of the image.
	 * @param y The y position of the image.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 * @return True if further updates needed, false if not,
	 *********************************************************************/
	@Override
	public boolean imageUpdate(final Image img, 
				final int infoflags, 
				final int x, 
				final int y, 
				final int width, 
				final int height) {
		return false;
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Moves the object image across the
	 * panel by a factor of 3 + speedFactor.
	 *********************************************************************/
	@Override
	public void decX() {
		xPos -= (3 + speedFactor);
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Returns the current x position of the 
	 * obstacle.
	 * 
	 * @return Current x position.
	 *********************************************************************/
	@Override
	public int getX() {
		return xPos;
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Returns the current y position of the 
	 * obstacle.
	 * 
	 * @return Current y position.
	 *********************************************************************/
	@Override
	public int getY() {
		return yPos;
	}

	/**********************************************************************
	 * Inherited from DinoObstacle. Returns the width of the obstacle.
	 * 
	 * @return Obstacle's width.
	 *********************************************************************/
	@Override
	public int getWidth() {
		return WIDTH;
	}

	/**********************************************************************
	 * Inherited from DinoObstacle. Returns the height of the obstacle.
	 * 
	 * @return Obstacle's height.
	 *********************************************************************/
	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Sets the x position back to default
	 * so the obstacle can move across the screen.
	 *********************************************************************/
	@Override
	public void resetX() {
		xPos = DEFAULTX;
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Increments the speed factor so the 
	 * obstacle moves across the screen faster.
	 *********************************************************************/
	@Override
	public void incSpeed() {
		speedFactor++;
	}
}
