package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import main.GamePanel;
import main.KeyHandler;
import main.Spritesheet;

public class Player extends Entity {

	private static final int ACTION_COUNT = 8;
	GamePanel gp;
	KeyHandler keyH;
	Spritesheet playerSprites;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		x = 100;
		y = 100;
		speed = 4;
		direction = Directions.down;
		try {
			playerSprites = new Spritesheet("/player/player.png", GamePanel.baseTileSize, GamePanel.baseTileSize, ACTION_COUNT);
		} catch (IOException e) {
			System.out.println("Unable to find player textures.");
			e.printStackTrace();
		}
	}
	
	public void getPlayerImage() {
		
	}
	
	public void update() {
		if (keyH.keyPressed(KeyEvent.VK_W)) {
			y -= speed;
			direction = Directions.up;
		} else if (keyH.keyPressed(KeyEvent.VK_A)) {
			x -= speed;
			direction = Directions.left;
		} else if (keyH.keyPressed(KeyEvent.VK_S)) {
			y += speed;
			direction = Directions.down;
		} else if (keyH.keyPressed(KeyEvent.VK_D)) {
			x += speed;
			direction = Directions.right;
		}
		
		spriteCounter++;
		if (spriteCounter > 10) {
			spriteNumber = spriteNumber*-1+1;
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		int textureIndex = switch (direction) {
		case Directions.up:
			yield Sprite.up1.ordinal();
		case Directions.left:
			yield Sprite.left1.ordinal();
		case Directions.right:
			yield Sprite.right1.ordinal();
		case Directions.down:
			yield Sprite.down1.ordinal();
		};
		
		textureIndex += spriteNumber;
		
		BufferedImage texture = playerSprites.getSprite(textureIndex);
		
		g2.drawImage(texture, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
	}
}
