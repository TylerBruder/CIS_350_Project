package cis.gvsu.edu;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**********************************************************************
 * 2D Snake game with features including score, colored borders, and 
 * functional movement of the snake. Snake dies when it eats itself, 
 * or touches the border.
 *@author Tyler Bruder
 *@version Summer 2018 - Release 1
 */
public class Game extends JPanel implements Runnable, KeyListener {

    /**serial version ID.*/
    private static final long serialVersionUID = 1L;

    /**Width and Height of the game board.*/
    public static final int WIDTH = 500, HEIGHT = 500;

    /**Game thread.*/
    private Thread thread;

    /**State of the game -- running or not.*/
    private boolean running;

    /**State of direction that snake is moving.*/
    private boolean right = true, left = false, up = false, down = false;

    /**Instance of snake body.*/
    private Body b;

    /**Instance of food placed.*/
    private Food food;

    /**Array list to hold snake body length.*/
    private ArrayList<Body> snake;

    /**Array list to hold food on board.*/
    private ArrayList<Food> foods;

    /**Helps randomize location of food.*/
    private Random r;

    /**Initializing coordinates of snake, size of snake, and score/ticks.*/
    private int xCoor = 10, yCoor = 10, size = 5, score = 0, ticks = 0;


    /******************************************************************
     *Constructor the creates the game, sets dimensions 
     *and adds aspects needed to run the game.
     */
    public Game() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        snake = new ArrayList<Body>();
        foods = new ArrayList<Food>();
        r = new Random();
        //Start game after everything is setup
        start();

    }


    /******************************************************************
     *Starts the game.
     *Prompts user if they are ready: Upon selecting 'ok', game will start
     */
    public void start() {
        Object[] options = {"OK"};
        JOptionPane.showOptionDialog(null,
                "Click OK to start. Good luck. ", "Get Ready",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /******************************************************************
     *Stops the game.
     *Informs the user their score, and advises them to restart the 
     *application to start again.
     */
    public void stop() {
        running = false;
        JOptionPane.showMessageDialog(
        		null, "Game over. Your score was: " + score + ". "
                + "\n \n \n Game will now close. "
                + "Please re-open to play again.");
        System.exit(0);
        try {
            thread.join(); //'Pauses' one thread until next is ready to execute
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /******************************************************************
     *Game tick that checks for a loss, score to be added, 
     *and all game feature.
     */
    public void tick() {
        if (snake.size() == 0) {
            b = new Body(xCoor, yCoor, 10);
            snake.add(b);
        }
        ticks++;
        if (ticks > 400000) { //controls game speed
            if (right) {
				xCoor++;
			}
            if (left) {
				xCoor--;
			}
            if (up) {
				yCoor--;
			}
            if (down) {
				yCoor++;
			}

            ticks = 0;
            b = new Body(xCoor, yCoor, 10);
            snake.add(b);

            if (snake.size() > size) {
                snake.remove(0);
            }
        }
        //If no food present, place a food randomly on the grid
        if (foods.size() == 0) {
            int xCoor = r.nextInt(48);
            int yCoor = r.nextInt(48);

            food = new Food(xCoor, yCoor, 10);
            foods.add(food);
        }
        //Snake eats a food, increment score, 
        //remove current instance of food, replace with another food
        for (int i = 0; i < foods.size(); i++) {
            if (xCoor == foods.get(i).getxCoor() 
            		&& yCoor == foods.get(i).getyCoor()) {
                size++;
                foods.remove(i);
                i++;
                score++;
            }
        }
        //Snake eats itself = loss
        for (int i = 0; i < snake.size(); i++) {
            if (xCoor == snake.get(i).getxCoor() 
            		&& yCoor == snake.get(i).getyCoor()) {
                if (i != snake.size() - 1) {
                    stop();
                }
            }
        }
        //Snake touches border, game is lost
        if (xCoor < 0 || xCoor > 48 || yCoor < 0 || yCoor > 48) {
            stop();
        }
    }


    /******************************************************************
     *Paints all graphics for the current game: 
     *Including board, snake, and food.
     *@param g Graphics field.
     */
    public void paint(final Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < WIDTH / 10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 10; i++) {
            g.drawLine(0, i * 10, HEIGHT, i * 10);
        }
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for (int i = 0; i < foods.size(); i++) {
            foods.get(i).draw(g);
        }
    }


    /*
     *Tells game to use ticks and to repaint every tick
     *Runs only while running is set to true
     */
    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }


    /******************************************************************
     *Responsible for movement of snake
     *Controlled by arrow keys on the keyboard for now.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
    	//gets key code from button pressed
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
        }
    }

    /******************************************************************
     *Necessary for keyListener to be implemented.
     *@param e KeyEvent that was sent.
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /******************************************************************
     *Necessary for keyListener to be implemented.
     *@param e KeyEvent that was sent.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
    }

}
