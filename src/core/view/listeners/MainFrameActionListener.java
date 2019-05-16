package core.view.listeners;

import java.awt.event.ActionListener;

import core.view.MainFrame;

public abstract class MainFrameActionListener implements ActionListener {

	private MainFrame mainFrame;

	public MainFrameActionListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	protected MainFrame getMainFrame() {
		return mainFrame;
	}
	
}
