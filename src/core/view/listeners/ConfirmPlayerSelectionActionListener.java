package core.view.listeners;

import java.awt.event.ActionEvent;

import core.view.MainFrame;

public class ConfirmPlayerSelectionActionListener extends MainFrameActionListener {

	public ConfirmPlayerSelectionActionListener(MainFrame mainFrame) {
		super(mainFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getMainFrame().confirmSelectedPlayer();
	}

}
