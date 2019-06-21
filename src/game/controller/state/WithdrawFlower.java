package game.controller.state;

import game.controller.GameController;
import game.model.GameStatus;
import game.model.flower.Flower;

public class WithdrawFlower extends AbstractControllerState {

	public WithdrawFlower(GameController gameController) {
		super(gameController);
	}

	@Override
	public void withdrawRedFlower(int index) throws Exception {
		Flower flower = gameController.removeRoundWithdrawRedFlower(index);
		gameController.addTurnRedFlower(flower);
		if(gameController.checkWithdrawedRedFlowers() && gameController.checkWithdrawedYellowFlowers()) {
			goToNextStep();
		}
	}

	@Override
	public void withdrawYellowFlower(int index) throws Exception {
		Flower flower = gameController.removeRoundWithdrawYellowFlower(index);
		gameController.addTurnYellowFlower(flower);
		if(gameController.checkWithdrawedYellowFlowers() && gameController.checkWithdrawedRedFlowers()) {
			goToNextStep();
		}
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.WITHDRAW_FLOWER;
	}
	
	private void goToNextStep() {
		gameController.setState(new FlowerSelection(gameController));
	}

}
