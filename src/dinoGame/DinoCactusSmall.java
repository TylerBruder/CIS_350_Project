package dinoGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DinoCactusSmall implements ImageObserver {
	private int xPos = 600;
	private int yPos = 245;
	private static final int WIDTH = 33;
	private static final int HEIGHT  = 67;
	
	private BufferedImage smallCactusImg;
	public DinoCactusSmall()
	{
		try {
			smallCactusImg = ImageIO.read(new File("sprites/cactusSmallMany0000.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(final Graphics g) {
		g.drawImage(smallCactusImg, xPos, yPos, WIDTH, HEIGHT, this);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}
	
	public void decX()
	{
		xPos-=2;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public void resetX()
	{
		xPos = 600;
	}
}