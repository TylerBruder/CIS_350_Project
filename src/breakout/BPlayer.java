package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BPlayer {

	private int x;
	private int xDiff;
	private int y;
	
	private final int left = 37;
	private final int right = 39;
	private static final int pWidth = 820;
	private static final int pHeight = 600;
	private static final int WIDTH = 120;
	private static final int HEIGHT = 10;
	
	public BPlayer() {
		x = pWidth / 2;
		y = pHeight - 50;
		xDiff = 0;
	}
	
	public void update() {
        if (x > 10 && x < pWidth - WIDTH - 20)
            x += xDiff;
        else if (x <= 20)
            x++;
        else if (x >= pWidth - WIDTH - 20)
            x--;
    }
	
	public void pressed(int key) {
		if (key == left)
			xDiff = -1;
		else if (key == right)
			xDiff = 1;
	}
	
	public void released(int key) {
        if (key == left || key == right)
            xDiff = 0;
    }
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
