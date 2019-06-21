package game.controller.state;

import game.controller.GameController;

public class JuniorGardenerFlowerSquare extends AbstractControllerState {

	public JuniorGardenerFlowerSquare(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Floração do jardineiro júnior";
	}

}
