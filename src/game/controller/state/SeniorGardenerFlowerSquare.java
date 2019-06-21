package game.controller.state;

import game.controller.GameController;

public class SeniorGardenerFlowerSquare extends AbstractControllerState {

	public SeniorGardenerFlowerSquare(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Floração do jardineiro sênior";
	}

}
