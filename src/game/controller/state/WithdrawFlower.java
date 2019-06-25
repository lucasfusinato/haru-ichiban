package game.controller.state;

import java.util.List;

import game.model.flower.Flower;
import game.model.game.GameStatus;

public class WithdrawFlower extends AbstractControllerState {

	public WithdrawFlower(GameControllerStateAccess gameController) {
		this(gameController, false);
	}
	
	public WithdrawFlower(GameControllerStateAccess gameController, boolean newTurn) {
		super(gameController);
		if(newTurn) {
			gameController.goToNextTurn();
		}
	}

	@Override
	public void withdrawRedFlower(int index) {
		Flower flower = gameController.removeRoundWithdrawRedFlower(index);
		gameController.addTurnRedFlower(flower);
		if(gameController.checkWithdrawedRedFlowers() && gameController.checkWithdrawedYellowFlowers()) {
			goToNextStep();
		}
	}

	@Override
	public void withdrawYellowFlower(int index) {
		Flower flower = gameController.removeRoundWithdrawYellowFlower(index);
		gameController.addTurnYellowFlower(flower);
		if(gameController.checkWithdrawedYellowFlowers() && gameController.checkWithdrawedRedFlowers()) {
			goToNextStep();
		}
	}
	
	@Override
	public boolean canWithdrawRedFlowerAt(int index) {
		return canWithdrawFlowerAt(index, gameController.getCurrentTurn().getRedFlowers());
	}
	
	@Override
	public boolean canWithdrawYellowFlowerAt(int index) {
		return canWithdrawFlowerAt(index, gameController.getCurrentTurn().getYellowFlowers());
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.WITHDRAW_FLOWER;
	}
	
	private void goToNextStep() {
		gameController.setState(new FlowerSelection(gameController));
	}

	private boolean canWithdrawFlowerAt(int index, List<Flower> scope) {
		return scope.size() <= index && gameController.getRoundQuantity() - gameController.getCurrentRound().getTurns().size() >= index;
	}

}
