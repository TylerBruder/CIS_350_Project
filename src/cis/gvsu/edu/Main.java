package cis.gvsu.edu;

import javax.swing.*;
import java.awt.*;



public class Main {

	public Main() {
		
		Game gamePanel = new Game();

		
		JFrame frame = new JFrame();

		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SnakeGame");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getRootPane().setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.BLUE));

	}

	public static void main(String[] args) {
		
		new Main();
	}
	
}
