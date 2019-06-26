package game.view.itemselection;

import game.controller.GameControllerInterface;
import game.view.itemselection.strategy.YellowItemSelectionStrategy;

@SuppressWarnings("serial")
public class YellowGardenerItemSelectionPanel extends AbstractItemSelectionPanel {

	public YellowGardenerItemSelectionPanel(GameControllerInterface gameController) {
		super(gameController, new YellowItemSelectionStrategy());
	}

}
