package core.view.listeners;

import java.awt.event.ActionEvent;

import core.view.MainFrame;

public class CancelLevelSelectionActionListener extends MainFrameActionListener {

	public CancelLevelSelectionActionListener(MainFrame mainFrame) {
		super(mainFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getMainFrame().cancelSelectedLevel();
	}

}
