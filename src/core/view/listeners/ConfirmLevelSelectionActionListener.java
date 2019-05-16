package core.view.listeners;

import java.awt.event.ActionEvent;

import core.view.MainFrame;

public class ConfirmLevelSelectionActionListener extends MainFrameActionListener {

	public ConfirmLevelSelectionActionListener(MainFrame mainFrame) {
		super(mainFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getMainFrame().confirmSelectedLevel();
	}

}
