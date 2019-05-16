package utils;

import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class ClickEnterKeyListener extends EnterKeyListener {

	private JButton button;

	public ClickEnterKeyListener(JButton button) {
		this.button = button;
	}

	@Override
	protected void execute(KeyEvent e) {
		button.requestFocus();
		button.doClick();
	}


}
