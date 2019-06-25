package game.controller.state;

import game.model.game.GameStatus;

public class SeniorGardenerDarkenedFrogSquare extends AbstractEndFlowState {

	public SeniorGardenerDarkenedFrogSquare(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public GameStatus getStatus() {
		return GameStatus.SENIOR_GARDENER_DARKENED_FROG_NENUFAR;
	}

}
