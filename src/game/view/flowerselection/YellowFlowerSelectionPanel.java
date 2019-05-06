package game.view.flowerselection;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class YellowFlowerSelectionPanel extends AbstractFlowerSelectionPanel {
	
	public YellowFlowerSelectionPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected YellowFlowerSelectionTable createFlowerSelectionTable() {
		return new YellowFlowerSelectionTable(getGameController());
	}
	
}
