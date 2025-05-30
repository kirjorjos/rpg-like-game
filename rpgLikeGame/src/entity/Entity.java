package entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Directions direction;
	
	public double spriteCounter = 0;
	public double spriteNumber = 0;
	
	public enum Sprite {
		left1,
		left2,
		right1,
		right2,
		down1,
		down2,
		up1,
		up2
	}
	
	public enum Directions {
		up,
		down,
		right,
		left
	}
}
