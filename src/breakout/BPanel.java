package breakout;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.ExecutorService;


public class BPanel extends JPanel implements ActionListener, KeyListener{
	
	private BRectangles[] blocks;
	private Color[] blockColors;
	private BPlayer player;
	private BBall ball;
	
	private final int maxBlocks = 60;
	
	private float re;
	private float gr;
	private float bl;
	private Random rand;

	public BPanel() {
		blocks = new BRectangles[maxBlocks];
		blockColors = new Color[maxBlocks];
		rand = new Random();
		
		for (int i = 0; i < maxBlocks; i++) {
			re = rand.nextFloat();
			gr = rand.nextFloat();
			bl = rand.nextFloat();
			
			while (re < 0.25 && gr < 0.25 && bl < 0.25) {
				re = rand.nextFloat();
				gr = rand.nextFloat();
				bl = rand.nextFloat();
			}			
			
			blockColors[i] = new Color(re, gr, bl);
		}
		
		int count = 0;
		for (int i = 0; i < maxBlocks / 6; i++)
			for (int j = 0; j < maxBlocks / 10; j++) {
				blocks[count] = new BRectangles(i, j, blockColors[count]);
				count++;
			}
		
		addKeyListener(this);
		setFocusable(true);
		Timer timer = new Timer(4, this);
		timer.start();
		player = new BPlayer();
		ball = new BBall();
				
		setBackground(Color.black);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < maxBlocks; i++)
			if (blocks[i] != null)
				blocks[i].paint(g);
		
		player.paint(g);
		ball.paint(g);
	}
	
	private void update() {
		player.update();
		ball.update();
		checkCollision();
	}
	
	private void checkCollision() {
		if (ball.getBounds().intersects(player.getBounds()))
			ball.switchDirection();
		
		for (int i = 0; i < blocks.length; i++)
			if (blocks[i] != null)
				if (ball.getBounds().intersects(blocks[i].getBounds())) {
					ball.switchDirection();
					blocks[i] = null;
					break;
				}
		
		if (checkWinner()) {
			JOptionPane.showMessageDialog(null, "You win!", null, JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
	
	private boolean checkWinner() {
		for (int i = 0; i < blocks.length; i++)
			if (blocks[i] != null)
				return false;
		return true;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		player.pressed(arg0.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		player.released(arg0.getKeyCode());
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//player.pressed(arg0.getKeyCode());
		;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}
}
