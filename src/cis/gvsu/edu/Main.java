package cis.gvsu.edu;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/***********************************************************************
*Responsible for putting the game together into something usable by an 
*end user.
*@author Tyler Bruder
*@version Summer 2018
 *********************************************************************/
public class Main {

	/******************************************************************
     *Constructor that adds the panels to main frame, and sets border up.
     */
    public Main() {

        Game gamePanel = new Game();
        JFrame frame = new JFrame();

        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SnakeGame");
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setBorder(
        		BorderFactory.createMatteBorder(
        				3, 3, 3, 3, Color.BLUE));
    }

    /**********************************************************************
     *Main method that starts the game.
     *@param args Args for main method.
     */
    public static void main(final String[] args) {

        new Main();
    }

}
