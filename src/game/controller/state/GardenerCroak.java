package game.controller.state;

import game.controller.GameController;
import game.model.GameStatus;

public class GardenerCroak extends AbstractControllerState {

	public GardenerCroak(GameController gameController) {
		super(gameController);
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.GARDENER_CROAK;
	}

}
