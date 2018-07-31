package dinoGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/***********************************************************************
 * DinoPanel class. This is where the logic happens. Swing timer
 * controls flow of events at a predetermined "frames per second".
 * 
 * @author kennemat
 * @version Summer 2018.
 **********************************************************************/
public class DinoPanel extends JPanel implements KeyListener, ActionListener{
	
	/** Default serial ID. */
	private static final long serialVersionUID = 1L;

	/** Dino player object. */
	private DinoPlayer player;
	
	/** Dino ground object. */
	private DinoGround ground;
	
	/** Framerate for the swing timer. */
	private static final int FRAMERATE = 60;
	
	/** Counter value to determine when the player should swap sprites. */
	private int switchLegs = -1;
	
	/** Max number of unique obstacles in the game. */
	private final static int MAXOBSTACLES = 4;
	
	/** Random number generator. */
	private static Random rand;
	
	/** Array to hold the specks on the ground. */
	private DinoSpeck[] specks;
	
	/** Determines when an obstacle should be released. */
	private int obstacleCounter;
	
	/** Holds what obstacle is currently being used. */
	private int obstacleRandom;
	
	/** Boolean value to check if an obstacle is currently in the game. */
	private boolean obstacleDrawn = false;
	
	/** Array of all obstacle objects. */
	private DinoObstacle[] obstacles;
	
	/** Final variable for big cactus in obstacles array. */
	private static final int BIGCACTUS = 0;
	
	/** Final variable for small cactus in obstacles array. */
	private static final int SMALLCACTUS = 1;
	
	/** Final variable for many cacti in obstacles array. */
	private static final int MANYCACTUS = 2;
	
	/** Final variable for bird in obstacles array. */
	private static final int BERD = 3;
	
	/** Number of specks on the ground. */
	private static final int MAXSPECKS = 20;
	
	/** Font object for the score label. */
	private Font scoreFont;
	
	/** JLabel to show the score. */
	private JLabel scoreLabel;
	
	/** Score value. */
	private int score;
	
	/** Swing timer to make events flow. */
	private Timer timer;
	
	/**********************************************************************
	 * Constructor for the DinoPanel class. Initializes values and sets up
	 * speck and obstacle arrays. Then adds components to panel.
	 *********************************************************************/
	public DinoPanel() {
		//Initialize player and ground objects.
		super.setBackground(Color.white);
		player = new DinoPlayer();
		ground = new DinoGround();
		
		//Create obstacles array, add obstacles.
		obstacles = new DinoObstacle[MAXOBSTACLES];
		obstacles[BIGCACTUS] = new DinoCactusBig();
		obstacles[SMALLCACTUS] = new DinoCactusSmall();
		obstacles[MANYCACTUS] = new DinoCactusMany();
		obstacles[BERD] = new DinoBerd();
		
		//Initialize specks array.
		specks = new DinoSpeck[MAXSPECKS];
		rand = new Random();
		for (int i = 0; i < MAXSPECKS; i++)
			specks[i] = new DinoSpeck();
		
		//Set up score label.
		scoreFont = new Font("Arial", 1, 16);
		scoreLabel = new JLabel("0");
		scoreLabel.setPreferredSize(new Dimension(100, 50));
		scoreLabel.setFont(scoreFont);
		score = 0;
		add(scoreLabel);
		
		//Initialize obstacle values to 0.
		obstacleCounter = 0;
		obstacleRandom = 0;
		
		//Add key listener and set up swing timer.
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(500/FRAMERATE, this);
		timer.start();
	}
	
	/**********************************************************************
	 * Paint component method. All painting of other objects on this panel
	 * has to be done from here. This panel determines what sprites should
	 * be used by player and bird, what obstacle is in use, what speed
	 * the game is moving at, and whether the player has lost.
	 * 
	 * @param g The graphics variable.
	 *********************************************************************/
	@Override
	public void paintComponent(final Graphics g) {
		//Clears what's on the panel.
		super.paintComponent(g);
		
		//If this method has run through 9 times, then do this.
		if (switchLegs > 9) {
			player.paint(g, true, false);
			switchLegs = -1;
			score++;
			
			//Speed the game up.
			if (score % 150 == 0)
			{
				for (DinoObstacle obstacle : obstacles)
					obstacle.incSpeed();
					
				for (DinoSpeck speck : specks)
					speck.incSpeed();
			}
			scoreLabel.setText(score + "");
		}
		
		//Move the specks along and repaint them.
		for (DinoSpeck speck : specks)
		{
			speck.decX();
			speck.paint(g);
		}
		
		//If the method has run through 300 times, then push an obstacle. */
		if (obstacleCounter > 300)
		{
			obstacleCounter = 0;
			obstacleRandom = rand.nextInt(MAXOBSTACLES);
			obstacleDrawn = true;
			
			obstacles[obstacleRandom].resetX();
		}
		
		//If an obstacle is on the board, then move it and paint it.
		if (obstacleDrawn)
		{			
			obstacles[obstacleRandom].decX();
			obstacles[obstacleRandom].paint(g);
			//Obstacle has moved past the end of the board.
			if (obstacles[obstacleRandom].getX() < -100)
				obstacleDrawn = false;
			
			//Check for collisions.
			if (checkCollisions())
			{
				super.paintComponent(g);
				player.paint(g, false, true);
				ground.paint(g, 930, 520);
				obstacles[obstacleRandom].paint(g);
				for (DinoSpeck speck : specks)
					speck.paint(g);
				timer.stop();
				return;
			}
		}
		
		ground.paint(g, 930, 520);
		player.paint(g, false, false);
		
		switchLegs += 1;
		if (!obstacleDrawn)
			obstacleCounter++;
	}
	
	/**********************************************************************
	 * Checks for collisions. This is done by getting the x, y, width, and
	 * height of both the player and whatever object is on the game. After
	 * this information is grabbed, creates two rectangles, and checks if
	 * they intersect.
	 * 
	 * @return True if the rectangles intersect, false if not.
	 *********************************************************************/
	private boolean checkCollisions()
	{
		int playerX = 15;
		int playerY = player.getY();
		int playerWidth = player.getWidth();
		int playerHeight = player.getHeight();
		Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
		
		int obstacleX = obstacles[obstacleRandom].getX();
		int obstacleY = obstacles[obstacleRandom].getY();
		int obstacleWidth = obstacles[obstacleRandom].getWidth();
		int obstacleHeight = obstacles[obstacleRandom].getHeight();
		Rectangle obstacleRect = new Rectangle(obstacleX, obstacleY, obstacleWidth, obstacleHeight);
		
		if (playerRect.intersects(obstacleRect))
			return true;
		return false;
	}

	/**********************************************************************
	 * Implemented method from ActionListener interface. This is what the
	 * swing timer calls every frame. This will repaint the panel.
	 * 
	 * @param e The action event that occured.
	 *********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}

	/**********************************************************************
	 * Implemented method from KeyListener interface. This is called when a
	 * key is type.
	 * 
	 * @param arg0 The key that was typed.
	 *********************************************************************/
	@Override
	public void keyTyped(final KeyEvent arg0) {
		;
	}

	/**********************************************************************
	 * Implemented method from KeyListener interface. This is called when a
	 * key is pressed.
	 * 
	 * @param arg0 The key that was pressed.
	 *********************************************************************/
	@Override
	public void keyPressed(final KeyEvent arg0) {
		player.pressed(arg0.getKeyCode());
		
	}

	/**********************************************************************
	 * Implemented method from KeyListener interface. This is called when a
	 * key is released.
	 * 
	 * @param arg0 The key that was released.
	 *********************************************************************/
	@Override
	public void keyReleased(final KeyEvent arg0) {
		player.released(arg0.getKeyCode());
	}
	
	/**********************************************************************
	 * Static method that generates a new random number. Used by this class
	 * and DinoSpeck class.
	 * 
	 * @param upperBound The upper limit for the random number.
	 * @param lowerBound The lower limit for the random number.
	 * @return The random number.
	 *********************************************************************/
	public static int getNewRand(int upperBound, int lowerBound)
	{
		return rand.nextInt(upperBound - lowerBound) + lowerBound;
	}
}
