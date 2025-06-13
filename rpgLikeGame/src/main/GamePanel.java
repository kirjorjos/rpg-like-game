package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import entity.Player;

import map.TileMap;
import map.WildernessMap;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * Screen Settings
	 */
	public final static int baseTileSize = 16;	//16px*16px
	private final static int scale = 3;
	public final static int tileSize = baseTileSize*scale;
	private final static int maxScreenCol = 16;
	private final static int maxScreenRow = 12;
	public final static int screenWidth = tileSize * maxScreenCol;
	public final static int screenHeight = tileSize * maxScreenRow;
	private final int FPS = 60;
	private final double nanoPerFrame = 1000000000/FPS;
	private Thread gameThread;
	private KeyHandler keyH = new KeyHandler();
	Player player = new Player(this, keyH);
	TileMap currentMap;
	
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
		
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		currentMap = new WildernessMap("/maps/Welcome.map");
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			long timeDifference = (currentTime - lastTime);
			delta += timeDifference / nanoPerFrame;
			lastTime = currentTime;
			timer += timeDifference;
			
			if (delta >= 1) {
				update();
				repaint();
				delta --;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				// System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		if (currentMap != null) currentMap.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
	
	@Override
	public void addNotify() {
	    super.addNotify();
	    requestFocusInWindow();
	}
}
