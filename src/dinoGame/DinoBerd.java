package dinoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/***********************************************************************
 * DinoBerd class for the bird obstacle. Implements the DinoObstacle
 * interface. Bird flies at constant speed across game.
 * 
 * 
 * @author mkennedy
 * @version Summer 2018.
 **********************************************************************/
public class DinoBerd implements ImageObserver, DinoObstacle {
	
	/** Default x position for the bird. */
	private static final int DEFAULTX = 930;
	
	/** Default y position for the bird. */
	private static final int DEFAULTY = 376;
	
	/** Width of the bird sprite. */
	private static final int WIDTH = 42;
	
	/** Height of the bird sprite. */
	private static final int HEIGHT  = 83;
	
	/** Actual x position variable. */
	private int xPos;
	
	/** Actual y position variable. */
	private int yPos;
	
	/** Speed factor for how fast the bird is moving. */
	private int speedFactor = 0;
	
	/** Counter to see if the images should be "flipped". */
	private int switchWingsCounter= 0;
	
	/** Boolean to determine which image is currently in use. */
	private boolean currWings;
	
	/** Buffered image variable for the first sprite. */
	private BufferedImage dinoBerd1;
	
	/** Buffered image variable for the second sprite. */
	private BufferedImage dinoBerd2;
	
	/**********************************************************************
	 * Constructor for the DinoBerd class. Initializes variables.
	 *********************************************************************/
	public DinoBerd() {
		try {
			dinoBerd1 = ImageIO.read(new File("sprites/berd.png"));
			dinoBerd2 = ImageIO.read(new File("sprites/berd2.png"));
		} catch (IOException e) {
			Object[] options = {"OK"};
	        JOptionPane.showOptionDialog(null,
	                "Error loading image files. ", "Error",
	                JOptionPane.PLAIN_MESSAGE,
	                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		}
		
		xPos = DEFAULTX;
		yPos = DEFAULTY;
	}
	
	/**********************************************************************
	 * Paint method. Determines which sprite should be painted on the
	 * panel, then paints that sprite.
	 * 
	 * @param g The graphics variable for the paint method.
	 *********************************************************************/
	public void paint(final Graphics g) {	
		switchWingsCounter+=1;
		if (switchWingsCounter > 90)
		{
			currWings = !currWings;
			switchWingsCounter = 0;
		}
		if (currWings)
			g.drawImage(dinoBerd1, xPos, yPos, WIDTH, HEIGHT, this);
		 else
			g.drawImage(dinoBerd2, xPos, yPos, WIDTH, HEIGHT, this);
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
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Moves the object image across the
	 * panel by a factor of 3 + speedFactor.
	 *********************************************************************/
	@Override
	public void decX()
	{
		xPos-=(3 + speedFactor);
	}
	
	/**********************************************************************
	 * Inherited from DinoObstacle. Returns the current x position of the 
	 * obstacle.
	 * 
	 * @return Current x position.
	 *********************************************************************/
	@Override
	public int getX()
	{
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
	public void resetX()
	{
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
