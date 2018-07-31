package dinoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/***********************************************************************
 * This is the player class. This class uses two sprites to convey
 * the dinosaur running. Stays at the same x, but can jump and duck
 * around obstacles.
 * 
 * @author kennemat
 * @version Summer 2018.
 **********************************************************************/
public class DinoPlayer implements ActionListener, ImageObserver {
	
	/** First image used to paint the dino running. */
	private BufferedImage playerImage1;
	
	/** Second image used to paint the dino running. */
	private BufferedImage playerImage2;
	
	/** Image used to paint the dino jumping. */
	private BufferedImage playerJumping;
	
	/** First image used to paint the player ducking. */
	private BufferedImage playerDucking1;
	
	/** Second image used to paint the player ducking. */
	private BufferedImage playerDucking2;
	
	/** Image used to paint the player dead. */
	private BufferedImage playerDead;
	
	/** Final value for the height of the running and jumping sprites. */
	private final static int HEIGHT = 93;
	
	/** Final value for the width of the running and jumping sprites. */
	private final static int WIDTH = 80;
	
	/** Final value for the width of the ducking sprites. */
	private static final int DUCKHEIGHT = 57;
	
	/** Final value for the height of the ducking sprites. */
	private static final int DUCKWIDTH = 113;
	
	/** Boolean value to determine which sprite is currently in use. */
	private boolean currPic;
	
	/** Final value for the key value for the up arrow key. */
	private static final int KEY_UP = 38;
	
	/** Final value for the key value for the space key. */
	private static final int KEY_SPACE = 32;
	
	/** Final value for the key value for the down arrow key. */
	private static final int KEY_DOWN = 40;
	
	/** Default y value for the dino, at ground level. */
	private static final int DEFAULTY = 447;
	
	/** Default jump speed for the dino, immediately when leaving the ground. */
	private static final int DEFAULTJUMP = 28;
	
	/** Default y position for the ducking sprite. */
	private static final int DEFAULTDUCKY = DEFAULTY + (HEIGHT - DUCKHEIGHT);
	
	/** Current x position of the dino. */
	private int yPos;
	
	/** Current y position of the dino. */
	private int xPos;
	
	/** Current jump speed of the dino. */
	private int jumpSpeed = DEFAULTJUMP;
	
	/** Gravity value which alters the jumpSpeed variable. */
	private static final int GRAVITY = 2;
	
	/** Boolean value to determine if player is jumping. */
	private boolean jumping;
	
	/** Boolean value to determine if player is ducking. */
	private boolean ducking;
	
	/** Swing timer to determine how fast gravity affects jumpSpeed. */
	private Timer timer;
	
	/**********************************************************************
	 * Constructor for the DinoPlayer class. Initializes variables, then
	 * starts the swing timer.
	 *********************************************************************/
	public DinoPlayer() {
		try {
			playerImage1 = ImageIO.read(new File("sprites/dinorun0000.png"));
			playerImage2 = ImageIO.read(new File("sprites/dinorun0001.png"));
			playerJumping = ImageIO.read(new File("sprites/dinoJump0000.png"));
			playerDucking1 = ImageIO.read(new File("sprites/dinoduck0000.png"));
			playerDucking2 = ImageIO.read(new File("sprites/dinoduck0001.png"));
			playerDead = ImageIO.read(new File("sprites/dinoDead0000.png"));
		} catch (IOException e) {
			Object[] options = {"OK"};
	        JOptionPane.showOptionDialog(null,
	                "Error loading image files. ", "Error",
	                JOptionPane.PLAIN_MESSAGE,
	                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		}
		
		yPos = DEFAULTY;
		xPos = 15;
		jumping = false;
		ducking = false;
		currPic = true;
		
		timer = new Timer(25, this);
		timer.start();
	}
	
	/**********************************************************************
	 * Called when a key is pressed. Determines if the key was the up key,
	 * down key, or space key.
	 * 
	 * @param key The key that was pressed.
	 *********************************************************************/
	public void pressed(final int key) {
		if ((key == KEY_UP || key == KEY_SPACE) && !jumping) {
			jumping = true;
		} else if (key == KEY_DOWN)
			ducking = true;			
	}
	
	/**********************************************************************
	 * Called when a key is released. Determines if the key was the down
	 * key.
	 * 
	 * @param key The key that was released.
	 *********************************************************************/
	public void released(final int key) {
		if (key == KEY_DOWN)
			ducking = false;
	}
	
	/**********************************************************************
	 * Repaints the player object. The logic for this is as follows:
	 * 	Checks if the player died. If so, paint dead player image, return.
	 * 	Checks if player is jumping. If so, paint player jumping, return.
	 * 	Checks if boolean to determine leg swap should switch.
	 * 	Checks if player is ducking. If so, duck according to currPic bool,
	 * 		return.
	 * 	Lastly, if still in the method, just paint the dino according to
	 * 		the currPic bool.
	 * 
	 * @param key The key that was released.
	 *********************************************************************/
    public void paint(final Graphics g, boolean switchLegs, boolean dead) {
    	if (dead)
    	{
    		g.drawImage(playerDead, xPos, yPos + 1, WIDTH, HEIGHT, this);
    		return;
    	}
    	
    	if (jumping)
    	{
    		g.drawImage(playerJumping, xPos, yPos, WIDTH, HEIGHT, this);
    		return;
    	}
    	if (switchLegs)
    		currPic = !currPic;
    	
    	if (ducking)
    	{
    		if (currPic)
    	    	g.drawImage(playerDucking1, xPos, DEFAULTDUCKY, DUCKWIDTH, DUCKHEIGHT, this);
    	    else
    	    	g.drawImage(playerDucking2, xPos, DEFAULTDUCKY, DUCKWIDTH, DUCKHEIGHT, this);
    		return;
    	}
	    	
    	if (currPic)
	    	g.drawImage(playerImage1, xPos, yPos, WIDTH, HEIGHT, this);
	    else
	    	g.drawImage(playerImage2, xPos, yPos, WIDTH, HEIGHT, this);
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
	 * Inherited from ActionListener. This is what the swing timer calls
	 * every given number of milliseconds. The method checks whether the
	 * player is jumping. If so, will continue to decrease the jumpSpeed
	 * by the value of gravity, until the player has reached ground level
	 * again.
	 * 
	 * @param e The action event that occured.
	 *********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (jumping && (yPos - (jumpSpeed - GRAVITY)) < DEFAULTY)
		{
			jumpSpeed -= GRAVITY;
			yPos -= jumpSpeed;
		}
		else
		{
			yPos = DEFAULTY;
			jumping = false;
			jumpSpeed = DEFAULTJUMP;
		}
	}
	
	/**********************************************************************
	 * Returns the current y value of the dino. If the dino is ducking,
	 * the y value needs to be skewed lower, as the y value is actually
	 * above the sprite for some reason.
	 * 
	 * @return The current y position of the dino.
	 *********************************************************************/
	public int getY() {
		if (ducking)
			return yPos + (DUCKHEIGHT / 2);
		return yPos;
	}
	
	/**********************************************************************
	 * Returns the width of the dino, according to whether the player is
	 * ducking or not.
	 * 
	 * @return Width of the dino.
	 *********************************************************************/
	public int getWidth()
	{
		if (ducking)
			return DUCKWIDTH;
		return WIDTH;
	}
	
	/**********************************************************************
	 * Returns the height of the dino, according to whether the player is
	 * ducking or not.
	 * 
	 * @return Width of the dino.
	 *********************************************************************/
	public int getHeight()
	{
		if (ducking)
			return DUCKHEIGHT;
		return HEIGHT;
	}
}
