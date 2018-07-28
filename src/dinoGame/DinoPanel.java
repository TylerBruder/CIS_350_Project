package dinoGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DinoPanel extends JPanel implements KeyListener, ActionListener{
	
	/** Dino player object. */
	private DinoPlayer player;
	
	private DinoGround ground;
	
	private static final int FRAMERATE = 60;
	
	private int switchLegs = -1;
	
	private final static int UPPERSPECKX = 610;
	private final static int LOWERSPECKX = 10;
	private final static int UPPERSPECKY = 390;
	private final static int LOWERSPECKY = 350;
	private final static int LOWERSPECKW = 4;
	private final static int UPPERSPECKW = 8;
	private final static int LOWERSPECKH = 1;
	private final static int UPPERSPECKH = 2;
	private final static int MAXOBSTACLES = 3;
	private final static int MINOBSTACLES = 1;
	
	private static Random rand;
	private DinoSpeck[] specks;
	
	private int obstacleCounter = 0;
	private int obstacleRandom = 0;
	private boolean obstacleDrawn = false;
	private DinoCactusBig bigCactus;
	private DinoCactusSmall smallCactus;
	private DinoCactusMany manyCactus;
	
	public DinoPanel() {
		super.setBackground(Color.white);
		player = new DinoPlayer();
		ground = new DinoGround();
		
		bigCactus = new DinoCactusBig();
		smallCactus = new DinoCactusSmall();
		manyCactus = new DinoCactusMany();
		
		
		addKeyListener(this);
		setFocusable(true);
		Timer timer = new Timer(500/FRAMERATE, this);
		timer.start();
		
		specks = new DinoSpeck[10];
		rand = new Random();
		for (int i = 0; i < 10; i++)
		{
			specks[i] = new DinoSpeck(getNewX(UPPERSPECKX, LOWERSPECKX), 
					getNewY(UPPERSPECKY, LOWERSPECKY), 
					getNewWidth(UPPERSPECKW, LOWERSPECKW), 
					getNewHeight(UPPERSPECKH, LOWERSPECKH));
		}
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (switchLegs > 9) {
//			super.paintComponent(g);
			player.paint(g, true);
			switchLegs = -1;
		}
		
		for (DinoSpeck speck : specks)
		{
			speck.decX();
			speck.paint(g);
		}
		
		if (obstacleCounter > 300)
		{
			obstacleCounter = 0;
			obstacleRandom = rand.nextInt(MAXOBSTACLES) + 1;
			obstacleDrawn = true;
			
			if (obstacleRandom == 1)
				bigCactus.resetX();
			else if (obstacleRandom == 2)
				smallCactus.resetX();
			else if (obstacleRandom == 3)
				manyCactus.resetX();
		}
		
		if (obstacleDrawn)
		{
			if (obstacleRandom == 1)
			{
				bigCactus.decX();
				bigCactus.paint(g);
				
				if (bigCactus.getX() < -10)
					obstacleDrawn = false;
			} else if (obstacleRandom == 2) {
				smallCactus.decX();
				smallCactus.paint(g);
				
				if (smallCactus.getX() < -10)
					obstacleDrawn = false;
			} else if (obstacleRandom == 3) {
				manyCactus.decX();
				manyCactus.paint(g);
				
				if (manyCactus.getX() < -10)
					obstacleDrawn = false;
			}
			
		}
		
		ground.paint(g);
		player.paint(g, false);
		
		switchLegs += 1;
		if (!obstacleDrawn)
			obstacleCounter++;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}

	@Override
	public void keyTyped(final KeyEvent arg0) {
		player.pressed(arg0.getKeyCode());
		
	}

	@Override
	public void keyPressed(final KeyEvent arg0) {
		player.pressed(arg0.getKeyCode());
		
	}

	@Override
	public void keyReleased(final KeyEvent arg0) {
		
	}
	
	
	public static int getNewX(int UPPERX, int LOWERX)
	{
		return rand.nextInt(UPPERX-LOWERX) + LOWERX;
	}
	
	public static int getNewY(int UPPERY, int LOWERY)
	{
		return rand.nextInt(UPPERY-LOWERY) + LOWERY;
	}
	
	public static int getNewWidth(int UPPERW, int LOWERW)
	{
		return rand.nextInt(UPPERW-LOWERW) + LOWERW;
	}
	
	public static int getNewHeight(int UPPERH, int LOWERH)
	{
		return rand.nextInt(UPPERH-LOWERH) + LOWERH;
	}
}
