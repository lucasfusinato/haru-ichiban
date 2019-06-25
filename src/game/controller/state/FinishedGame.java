package game.controller.state;

import game.model.game.GameStatus;

public class FinishedGame extends AbstractControllerState {

	public FinishedGame(GameControllerStateAccess gameController) {
		super(gameController);
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.FINISHED_GAME;
	}

}
