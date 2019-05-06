package game.view.flowerselection;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class RedFlowerSelectionPanel extends AbstractFlowerSelectionPanel {

	public RedFlowerSelectionPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected RedFlowerSelectionTable createFlowerSelectionTable() {
		return new RedFlowerSelectionTable(getGameController());
	}

}
