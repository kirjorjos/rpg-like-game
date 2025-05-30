package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("WIP 2D Game");
		window.setVisible(true);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		gamePanel.requestFocusInWindow();
		
		gamePanel.startGameThread();
	}
}
