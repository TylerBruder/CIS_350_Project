package dinogame;

import java.awt.Graphics;

/***********************************************************************
 * DinoSpeck class for the specks on the ground. Serve no functional
 * purpose other to convey speed to the user.
 * 
 * @author kennemat
 * @version Summer 2018.
 **********************************************************************/
public class DinoSpeck {
	
	/** Current x position of the speck. */
	private int xPos;
	
	/** Current y position of the speck. */
	private int yPos;
	
	/** Current width of the speck. */
	private int width;
	
	/** Current height of the speck. */
	private int height;
	
	/** Upper x limit the speck can appear at. */
	private static final int UPPERX = 910;
	
	/** Lower x limit the speck can appear at. */
	private static final int LOWERX = 10;
	
	/** Upper y limit the speck can appear at. */
	private static final int UPPERY = 550;
	
	/** Lower y limit the speck can appear at. */
	private static final int LOWERY = 530;
	
	/** Upper width limit the speck can appear at. */
	private static final int UPPERW = 8;
	
	/** Lower width limit the speck can appear at. */
	private static final int LOWERW = 4;
	
	/** Upper height limit the speck can appear at. */
	private static final int UPPERH = 2;
	
	/** Lower height limit the speck can appear at. */
	private static final int LOWERH = 1;
	
	/** Speed factor for how fast the speck is moving. */
	private int speedFactor;
	
	/**********************************************************************
	 * Constructor for the DinoSpeck class. Initializes variables.
	 *********************************************************************/
	public DinoSpeck() {
		speedFactor = 0;
		xPos = DinoPanel.getNewRand(UPPERX, LOWERX);
		yPos = DinoPanel.getNewRand(UPPERY, LOWERY);
		width = DinoPanel.getNewRand(UPPERW, LOWERW);
		height = DinoPanel.getNewRand(UPPERH, LOWERH);
	}
	
	/**********************************************************************
	 * Paints specks on the ground using the fillRect method. Gets random
	 * values for the specks to be placed at.
	 * 
	 * @param g The graphics variable for the paint method.
	 *********************************************************************/
	public void paint(final Graphics g) {
		if (xPos < -5) {
			xPos = DinoPanel.getNewRand(UPPERX + 60, UPPERX + 30);
			yPos = DinoPanel.getNewRand(UPPERY, LOWERY);		
			width = DinoPanel.getNewRand(UPPERW, LOWERW);
			height = DinoPanel.getNewRand(UPPERH, LOWERH);
		}
		g.fillRect(xPos, yPos, width, height);
	}
	
	/**********************************************************************
	 * Moves the speck across the panel by a factor of 3 + speedFactor.
	 *********************************************************************/
	public void decX() {
		xPos -= (3 + speedFactor);
	}
	
	/**********************************************************************
	 * Increments the speed factor so the speck moves across the screen 
	 * faster.
	 *********************************************************************/
	public void incSpeed() {
		speedFactor++;
	}
}
