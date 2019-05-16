package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class EnterKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			execute(e);
		}
	}

	protected abstract void execute(KeyEvent e);

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
