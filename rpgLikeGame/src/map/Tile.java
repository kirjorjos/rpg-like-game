package map;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage texture;
	private boolean collision;

	public Tile(BufferedImage texture, boolean collision) {
		this.texture = texture;
		this.collision = collision;
	}

	public BufferedImage getTexture() {
		return texture;
	}
}
