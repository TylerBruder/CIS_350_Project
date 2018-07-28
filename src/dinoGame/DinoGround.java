package dinoGame;

import java.awt.Graphics;
import java.util.Random;

public class DinoGround {
	private int yPos = 250;
	private int xPos = 0;
	
	public void paint(final Graphics g) {
		g.drawLine(0, 343, 600, 343);
	}

}
