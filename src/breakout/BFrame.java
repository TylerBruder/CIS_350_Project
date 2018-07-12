package breakout;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class BFrame extends JFrame {

	public BFrame() {
		BPanel panel = new BPanel();
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setSize(805, 620);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BFrame();
	}
}
