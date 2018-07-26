package dinoGame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import breakout.BPanel;

public class DinoFrame extends JFrame{
	
	/** Width of the frame. */
	private static final int DWIDTH = 600;
	
	/** Height of the frame. */
	private static final int DHEIGHT = 430;
	
	public DinoFrame() {
		DinoPanel panel = new DinoPanel();
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setSize(DWIDTH, DHEIGHT);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DinoFrame();
	}
}
