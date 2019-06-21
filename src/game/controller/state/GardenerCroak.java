package game.controller.state;

import game.controller.GameController;

public class GardenerCroak extends AbstractControllerState {

	public GardenerCroak(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Aguardando jardineiro coaxar";
	}

}
