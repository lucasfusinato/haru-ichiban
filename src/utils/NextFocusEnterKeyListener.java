package utils;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;

public class NextFocusEnterKeyListener extends EnterKeyListener {

	private JComponent nextComponent;

	public NextFocusEnterKeyListener(JComponent nextComponent) {
		this.nextComponent = nextComponent;
	}

	@Override
	protected void execute(KeyEvent e) {
		nextComponent.requestFocus();
	}


}
