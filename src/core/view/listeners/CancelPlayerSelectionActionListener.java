package core.view.listeners;

import java.awt.event.ActionEvent;

import core.view.MainFrame;

public class CancelPlayerSelectionActionListener extends MainFrameActionListener {

	public CancelPlayerSelectionActionListener(MainFrame mainFrame) {
		super(mainFrame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getMainFrame().cancelSelectedPlayer();
	}

}
