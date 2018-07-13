package cis.gvsu.edu;

import java.awt.Color;
import java.awt.Graphics;


/**********************************************************************
 *Responsible for creating the snake body and drawing it.
 *
 *@author Tyler Bruder
 *@version Summer 2018
 */
public class Body {

    /**Coordinates to place snake body 
     * and bounds to assure it stays in the frame.*/
    private int xCoor, yCoor, width, height;


    /**********************************************************************
     *Constructor that takes the coordinates that snake 
     *will be placed at, and how big each body part is.
     *@param xCoor x coordinate that snake will be placed at.
     *@param yCoor y coordinate that snake will be placed at.
     *@param tileSize Size of the tile.
     */
    public Body(final int xCoor, final int yCoor, final int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }

    /**********************************************************************
     *Sets the color and draws the snake body.
     *@param g Graphics field
     */
    public void draw(final Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }


    /**********************************************************************
     *Getter that gets x Coordinate.
     *@return The x coordinate.
     */
    public int getxCoor() {
        return xCoor;
    }


    /**********************************************************************
     *Getter that gets y Coordinate.
     *@return The y coordinate.
     */
    public int getyCoor() {
        return yCoor;
    }

}
