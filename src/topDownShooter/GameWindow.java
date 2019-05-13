package topDownShooter;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow {
	public GameWindow(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title);
		
		//size of the frame
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
