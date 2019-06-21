package game.controller.state;

import game.controller.GameController;

public class SeniorGardenerFrogSquare extends AbstractControllerState {

	public SeniorGardenerFrogSquare(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Escolha de casa para o sapo após floração";
	}

}
