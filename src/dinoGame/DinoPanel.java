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
	

	
	private static Random randX;
	private static Random randY;
	private static Random randWidth;
	private static Random randHeight;
	private DinoSpeck[] specks;
	
	private final static int UPPERX = 610;
	private final static int LOWERX = 10;
	private final static int UPPERY = 390;
	private final static int LOWERY = 350;
	private final static int LOWERW = 4;
	private final static int UPPERW = 8;
	private final static int LOWERH = 1;
	private final static int UPPERH = 2;
	
	
	public DinoPanel() {
		super.setBackground(Color.white);
		player = new DinoPlayer();
		ground = new DinoGround();
		addKeyListener(this);
		setFocusable(true);
		Timer timer = new Timer(1000/FRAMERATE, this);
		timer.start();
		
		specks = new DinoSpeck[10];
		randX = new Random();
		randY = new Random();
		randWidth = new Random();
		randHeight = new Random();
		for (int i = 0; i < 10; i++)
		{
			specks[i] = new DinoSpeck(getNewX(), getNewY(), getNewWidth(), getNewHeight());
		}
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		if (switchLegs > 5) {
			super.paintComponent(g);
			player.paint(g, true);
			switchLegs = -1;
			
			for (DinoSpeck speck : specks)
			{
				speck.decX();
				speck.paint(g);
			}
		}
		ground.paint(g);
		player.paint(g, false);
		
		
		
		switchLegs += 1;
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
	
	
	public static int getNewX()
	{
		return randX.nextInt(UPPERX-LOWERX) + LOWERX;
	}
	
	public static int getNewY()
	{
		return randY.nextInt(UPPERY-LOWERY) + LOWERY;
	}
	
	public static int getNewWidth()
	{
		return randWidth.nextInt(UPPERW-LOWERW) + LOWERW;
	}
	
	public static int getNewHeight()
	{
		return randWidth.nextInt(UPPERH-LOWERH) + LOWERH;
	}
	
	
}
