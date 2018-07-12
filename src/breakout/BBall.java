package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JOptionPane;

public class BBall {
	private static final int pWidth = 810;
	private static final int pHeight = 600;
	private static final int WIDTH = 15;
	private static final int HEIGHT = 15;
	private int x;
	private int y;
	private int xDiff;
	private int yDiff;
	
	public BBall() {
		Random rand = new Random();
		x = rand.nextInt(pWidth - 50) + 50;
		y = pHeight / 2 - 150;
	
		xDiff = 1;
		yDiff = 1;
	}
	
	public void update() {
		x+= xDiff;
		y+= yDiff;
		
        if (y > pHeight - HEIGHT - 20) {
        	JOptionPane.showMessageDialog(null, "You lose.", null, JOptionPane.PLAIN_MESSAGE);
        	System.exit(0);
        } else if (y == 0)
        	yDiff = -yDiff;
        else if (x <= 0 || x >= pWidth - WIDTH - 14)
            xDiff = -xDiff;
	}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
	
	public void switchDirection() {
		yDiff = -yDiff;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}
