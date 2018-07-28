package dinoGame;

import java.awt.Graphics;

public class DinoSpeck {

	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
	private final static int UPPERX = 610;
	private final static int LOWERX = 10;
	private final static int UPPERY = 390;
	private final static int LOWERY = 350;
	private final static int LOWERW = 4;
	private final static int UPPERW = 8;
	private final static int LOWERH = 1;
	private final static int UPPERH = 2;
	
	public DinoSpeck(int x, int y, int width, int height)
	{
		xPos = x;
		yPos = y;
		this.width = width;
		this.height = height;
	}
	
	public void paint(final Graphics g) {
		if (xPos < -5)
		{
			yPos = DinoPanel.getNewY(UPPERX, LOWERX);
			xPos = 610;
			width = DinoPanel.getNewWidth(UPPERW, LOWERW);
			height = DinoPanel.getNewHeight(UPPERH, LOWERH);
		}
		g.fillRect(xPos, yPos, width, height);
	}
	
	public void decX()
	{
		xPos-=2;
	}
	
	public void setY(int y)
	{
		yPos = y;
	}
}
