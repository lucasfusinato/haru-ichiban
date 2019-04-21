package game.view.flowerselection;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;

@SuppressWarnings("serial")
public class YellowFlowerSelectionPanel extends AbstractFlowerSelectionPanel {
	
	public YellowFlowerSelectionPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public GardenerColor getFlowerColor() {
		return GardenerColor.YELLOW;
	}
	
}
