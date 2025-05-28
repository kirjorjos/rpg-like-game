package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	private final int HIGHEST_VALID_KEYCODE = 65535;
	private boolean[] keysPressedDown = new boolean[HIGHEST_VALID_KEYCODE+1];

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + e.getKeyCode());
	    try {
	        keysPressedDown[e.getKeyCode()] = true;
	    } catch (ArrayIndexOutOfBoundsException ignored) {
	        // Silently ignore bad key codes like VK_UNDEFINED
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    try {
	        keysPressedDown[e.getKeyCode()] = false;
	    } catch (ArrayIndexOutOfBoundsException ignored) {
	        // Silently ignore bad key codes
	    }
	}
	
	public boolean keyPressed(int code) {
		return keysPressedDown[code];
	}

}
