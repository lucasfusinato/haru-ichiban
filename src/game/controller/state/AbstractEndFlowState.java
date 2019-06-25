package game.controller.state;

import game.controller.GameController;

public abstract class AbstractEndFlowState extends AbstractControllerState {

	public AbstractEndFlowState(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	protected void endFlow() {
		if(goToWithdrawFlowerState()) {
			gameController.setState(new WithdrawFlower(gameController, true));
		} else {
			gameController.setState(new FlowerSelection(gameController, true));
		}
	}

	private boolean goToWithdrawFlowerState() {
		int turnLimit = gameController.getRoundQuantity();
		int turns = gameController.getCurrentRound().getTurns().size();
		int availableTurns = turnLimit - turns;
		return (availableTurns >= GameController.AVAILABLE_SELECT_FLOWERS || availableTurns <= 0);
	}

}
