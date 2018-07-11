package cis.gvsu.edu;

import java.awt.Color;
import java.awt.Graphics;


/*
 *Responsible for creating the snake body and drawing it
 *
 *@Author Tyler Bruder
 *@Version Summer 2018
 */
public class Body {

    /*Coordinates to place snake body and bounds to assure it stays in the frame*/
    private int xCoor, yCoor, width, height;


    /*
     *Constructor that takes the coordinates that snake will be placed at, and how big each body part is
     *@Param xCoor
     *@Param yCoor
     *@Param tileSize
     */
    public Body(int xCoor, int yCoor, int tileSize) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }

    /*
     *Sets the color and draws the snake body
     *@Param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
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
