package cis.gvsu.edu;

import java.awt.Color;
import java.awt.Graphics;


/*
 *Responsible for creating the food and drawing it
 *
 *@Author Tyler Bruder
 *@Version Summer 2018
 */
public class Food {

	/*Coordinates to place food and bounds to assure it stays in the frame*/
	private int xCoor, yCoor, width, height;


	/*
	 *Constructor that takes the coordinates that food will be placed at, and how big the food is
	 *@Param xCoor
	 *@Param yCoor
	 *@Param tileSize
	 */
	public Food(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}

	/*
	 *Sets the color and draws the food
	 * @Param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	/*
	 *Getter that gets x Coordinate
	 */
	public int getxCoor() {
		return xCoor;
	}

	/*
	 *Getter that gets y Coordinate
	 */
	public int getyCoor() {
		return yCoor;
	}

}
