package cis.gvsu.edu;

import java.awt.Color;
import java.awt.Graphics;


/**********************************************************************
 *Responsible for creating the food and drawing it.
 *
 *@author Tyler Bruder
 *@version Summer 2018
 */
public class Food {

	/**Coordinates to place food and bounds to assure it stays in frame.*/
	private int xCoor, yCoor, width, height;


	/******************************************************************
	 *Constructor that takes the coordinates that 
	 *food will be placed at, and how big the food is.
	 *@param xCoor x coordinate of the food.
	 *@param yCoor y coordinate of the food.
	 *@param tileSize Tile size.
	 */
	public Food(final int xCoor, final int yCoor, final int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}

	/******************************************************************
	 *Sets the color and draws the food.
	 * @param g Graphics field.
	 */
	public void draw(final Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	/******************************************************************
	 *Getter that gets x Coordinate.
	 *@return x coordinate of the food.
	 */
	public int getxCoor() {
		return xCoor;
	}

	/******************************************************************
	 *Getter that gets y Coordinate.
	 *@return y coordinate of the food.
	 */
	public int getyCoor() {
		return yCoor;
	}

}
