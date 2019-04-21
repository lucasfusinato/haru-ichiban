package game.view.flowerselection;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;

@SuppressWarnings("serial")
public class RedFlowerSelectionPanel extends AbstractFlowerSelectionPanel {

	public RedFlowerSelectionPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public GardenerColor getFlowerColor() {
		return GardenerColor.RED;
	}

}
