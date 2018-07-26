package dinoGame;

import java.awt.Graphics;

public class DinoSpeck {

	private int xPos;
	private int yPos;
	private int width;
	private int height;
	
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
			yPos = DinoPanel.getNewY();
			xPos = 610;
			width = DinoPanel.getNewWidth();
			height = DinoPanel.getNewHeight();
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
