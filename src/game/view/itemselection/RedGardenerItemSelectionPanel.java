package game.view.itemselection;

import game.controller.GameControllerInterface;
import game.view.itemselection.strategy.RedItemSelectionStrategy;

@SuppressWarnings("serial")
public class RedGardenerItemSelectionPanel extends AbstractItemSelectionPanel {

	public RedGardenerItemSelectionPanel(GameControllerInterface gameController) {
		super(gameController, new RedItemSelectionStrategy());
	}

}
