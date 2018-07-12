package mainMenu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu implements ActionListener {
	JFrame frame;
	JButton button;
	JPanel panel;
	JLabel label = new JLabel("worked");
	
	public MainMenu()
	{
		//test
		frame = new JFrame();
		button = new JButton();
		panel = new JPanel();
		button.setPreferredSize(new Dimension(100, 30));
		button.addActionListener(this);
		panel.add(button);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame = new breakout.BFrame();
		panel.add(label);
	}
	

	public static void main(String[] args)
	{
		new MainMenu();
	}
	
}
