package game.controller.state;

import game.controller.GameController;

public class WithdrawFlower extends AbstractControllerState {

	public WithdrawFlower(GameController gameController) {
		super(gameController);
	}

	@Override
	public String toString() {
		return "Saque de flores";
	}

}
