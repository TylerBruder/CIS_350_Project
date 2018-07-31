package dinoGame;

import javax.swing.JDialog;
import javax.swing.JFrame;

/***********************************************************************
 * DinoFrame class. This is the frame which holds the content pane for
 * the dino game. All sprites in this game were found on CodeBullet's
 * github. No code was used, as I don't understand the language his game
 * is in.
 * 
 * Github: https://github.com/Code-Bullet/Google-Chrome-Dino-Game-AI
 * @author kennemat
 * @version Summer 2018.
 **********************************************************************/
public class DinoFrame extends JFrame {
	
	/** Default serial ID. */
	private static final long serialVersionUID = 1L;

	/** Width of the frame. */
	private static final int DWIDTH = 900;
	
	/** Height of the frame. */
	private static final int DHEIGHT = 600;
	
	/**********************************************************************
	 * Constructor for the DinoFrame class. Initializes panel and adds
	 * components.
	 *********************************************************************/
	public DinoFrame() {
		DinoPanel panel = new DinoPanel();
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setSize(DWIDTH, DHEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**********************************************************************
	 * Main method. Entry point for the program.
	 *********************************************************************/
	public static void main(String[] args) {
		new DinoFrame();
	}
}
