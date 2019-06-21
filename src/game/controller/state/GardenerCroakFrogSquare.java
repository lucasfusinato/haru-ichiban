package game.controller.state;

import game.controller.GameController;

public class GardenerCroakFrogSquare extends AbstractControllerState {

	public GardenerCroakFrogSquare(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Escolha de casa para o sapo após coaxar";
	}

}
