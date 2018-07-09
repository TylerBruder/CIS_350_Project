package cis.gvsu.edu;

import javax.swing.JFrame;

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
		
	}
	
	public static void main(String[] args) {
		
		new Main();
	}
	
}
