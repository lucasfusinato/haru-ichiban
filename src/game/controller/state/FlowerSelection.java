package game.controller.state;

import game.controller.GameController;

public class FlowerSelection extends AbstractControllerState {

	public FlowerSelection(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Seleção de flores";
	}

}
