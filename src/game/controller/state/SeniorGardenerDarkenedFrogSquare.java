package game.controller.state;

import game.controller.GameController;

public class SeniorGardenerDarkenedFrogSquare extends AbstractControllerState {

	public SeniorGardenerDarkenedFrogSquare(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Escolha de casa para o sapo ap�s escolha de nen�far escuro";
	}

}
