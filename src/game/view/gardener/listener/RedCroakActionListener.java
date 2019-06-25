package game.view.gardener.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.view.MainUtils;
import game.controller.GameControllerInterface;

public class RedCroakActionListener implements ActionListener {

	private GameControllerInterface gameController;

	public RedCroakActionListener(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.gameController.redGardenerCroak();
		} catch (Exception ex) {
			MainUtils.catchException(ex);
		}
	}

}
