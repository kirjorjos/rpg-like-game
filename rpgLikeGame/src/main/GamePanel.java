package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * Screen Settings
	 */
	public static final int baseTileSize = 16;	//16px*16px
	private static final int scale = 3;
	public static final int tileSize = baseTileSize*scale;
	private final int maxScreenCol = 16;
	private final int maxScreenRow = 12;
	private final int screenWidth = tileSize * maxScreenCol;
	private final int screenHeight = tileSize * maxScreenRow;
	private final int FPS = 60;
	private final int msPerFrame = 1000/FPS;
	private Thread gameThread;
	private KeyHandler keyH = new KeyHandler();
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		long nextFrameTime = System.currentTimeMillis();
		while (gameThread != null) {
			update();
			repaint();
			nextFrameTime = System.currentTimeMillis() + msPerFrame;
			long sleepTime = nextFrameTime-System.currentTimeMillis();
			if (sleepTime < 0) {
				sleepTime = 0;
				System.out.println("Game is lagging!");
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);
		g2.dispose();
	}
}
