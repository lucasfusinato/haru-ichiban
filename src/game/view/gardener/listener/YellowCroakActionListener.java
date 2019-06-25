package game.view.gardener.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.view.MainUtils;
import game.controller.GameControllerInterface;

public class YellowCroakActionListener implements ActionListener {

	private GameControllerInterface gameController;

	public YellowCroakActionListener(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			this.gameController.yellowGardenerCroak();
		} catch (Exception ex) {
			MainUtils.catchException(ex);
		}
	}

}
