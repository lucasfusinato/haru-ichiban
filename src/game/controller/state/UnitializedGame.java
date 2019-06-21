package game.controller.state;

import game.controller.GameController;

public class UnitializedGame extends AbstractControllerState {

	public UnitializedGame(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Jogo não iniciado";
	}

}
