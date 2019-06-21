package game.controller.state;

import game.controller.GameController;

public class FinishedGame extends AbstractControllerState {

	public FinishedGame(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Jogo finalizado";
	}

}
