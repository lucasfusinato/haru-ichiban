package utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class TabKeyAdapter extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyChar() == KeyEvent.VK_TAB) {
			execute(event);
		}
	}

	protected abstract void execute(KeyEvent event);

}
