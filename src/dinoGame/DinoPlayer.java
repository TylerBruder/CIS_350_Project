package dinoGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class DinoPlayer implements ActionListener, ImageObserver {
	
	/** Image to use to paint the dino. */
	private BufferedImage playerImage1;
	private BufferedImage playerImage2;
	private BufferedImage playerJumping;
	
	//private static final int 
	
	private boolean currPic;
	
	private static final int KEY_UP = 38;
	private static final int KEY_SPACE = 32;
	private int yPos = 250;
	private int xPos = 0;
	
	private int jumpSpeed = 15;
	private static final int GRAVITY = 1;
	private boolean jumping = false;
	
	public DinoPlayer() {
		try {
			playerImage1 = ImageIO.read(new File("sprites/dinorun0000.png"));
			playerImage2 = ImageIO.read(new File("sprites/dinorun0001.png"));
			playerJumping = ImageIO.read(new File("sprites/dinoJump0000.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currPic = true;
		Timer timer = new Timer(25, this);
		timer.start();
	}
	
	public void pressed(final int key) {
		if ((key == KEY_UP || key == KEY_SPACE) && !jumping) {
			jumping = true;
		}
			
	}
	
	
    public void paint(final Graphics g, boolean switchLegs) {
    	if (jumping)
    	{
    		g.drawImage(playerJumping, xPos, yPos, 80, 93, this);
    		return;
    	}
    	if (switchLegs)
    		currPic = !currPic;
	    	
    	if (currPic)
	    	g.drawImage(playerImage1, xPos, yPos, 80, 93, this);
	    else
	    	g.drawImage(playerImage2, xPos, yPos, 80, 93, this);
    }


	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jumping && (yPos - (jumpSpeed - GRAVITY)) < 250)
		{
			jumpSpeed -= GRAVITY;
			yPos -= jumpSpeed;
		}
		else
		{
			yPos = 250;
			jumping = false;
			jumpSpeed = 15;
		}
	}
}
