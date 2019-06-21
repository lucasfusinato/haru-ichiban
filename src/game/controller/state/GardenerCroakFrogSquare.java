package game.controller.state;

import game.controller.GameController;
import game.model.GameStatus;

public class GardenerCroakFrogSquare extends AbstractControllerState {

	public GardenerCroakFrogSquare(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public GameStatus getStatus() {
		return GameStatus.GARDENER_CROAK_FROG_SQUARE;
	}

}
