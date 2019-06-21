package game.controller.state;

import game.controller.GameController;
import game.model.GameStatus;

public class FinishedGame extends AbstractControllerState {

	public FinishedGame(GameController gameController) {
		super(gameController);
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.FINISHED_GAME;
	}

}
