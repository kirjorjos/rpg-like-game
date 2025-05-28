package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	
	private int spriteWidth;
	private int spriteHeight;
	private int spriteCount;
	private BufferedImage sheet;
	private BufferedImage[] sprites;
	
	public Spritesheet(String path, int spriteWidth, int spriteHeight, int spriteCount) throws IOException {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.spriteCount = spriteCount;
		this.sheet = ImageIO.read(getClass().getResourceAsStream(path));
		this.sprites = loadSprites();
	}
	
	public BufferedImage getSprite(int index) {
		return sprites[index];
	}
	
	private BufferedImage[] loadSprites() {
		BufferedImage[] sprites = new BufferedImage[spriteCount];
		for (int i = 0; i < spriteCount; i++) {
			sprites[i] = getTexture(i);
		}
		return sprites;
	}

	private BufferedImage getTexture(int index) {
		int spritesPerRow = sheet.getWidth() / spriteWidth;
		int spritesPerCol = sheet.getHeight() / spriteHeight;
		int maxSprites = spritesPerRow * spritesPerCol;

		if (index >= maxSprites) {
			throw new IllegalArgumentException("Index " + index + " is too high. Max allowed is " + (maxSprites - 1));
		}

		int x = (index % spritesPerRow) * spriteWidth;
		int y = (index / spritesPerRow) * spriteHeight;

		return sheet.getSubimage(x, y, spriteWidth, spriteHeight);
	}

}
