package cis.gvsu.edu;

import java.awt.Color;
import java.awt.Graphics;

public class Body {

	private int xCoor,yCoor,width,height;
	
	public Body(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}


	public int getxCoor() {
		return xCoor;
	}


	public int getyCoor() {
		return yCoor;
	}

}
