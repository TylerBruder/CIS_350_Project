package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BRectangles {
	private int x;
	private int y;
	
	private Color bColor;
	
	private static final int width = 80;
	private static final int height = 20;
	
	public BRectangles(int row, int col, Color bColor) {
		x = width * row;
		y = height * col;
		this.bColor = bColor;
	}
	
	public void paint(Graphics g) {
		g.setColor(bColor);
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
