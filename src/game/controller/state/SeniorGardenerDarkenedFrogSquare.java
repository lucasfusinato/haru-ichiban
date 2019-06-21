package game.controller.state;

import game.controller.GameController;
import game.model.GameStatus;

public class SeniorGardenerDarkenedFrogSquare extends AbstractControllerState {

	public SeniorGardenerDarkenedFrogSquare(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public GameStatus getStatus() {
		return GameStatus.SENIOR_GARDENER_DARKENED_FROG_NENUFAR;
	}

}
