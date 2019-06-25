package game.controller.state;

import game.model.game.GameStatus;

public class GardenerCroak extends AbstractControllerState {

	public GardenerCroak(GameControllerStateAccess gameController) {
		super(gameController);
		gameController.requestCroak();
	}
	
	@Override
	public void redGardenerCroak() {	
		gameController.setCroakGardener(gameController.getRedGardener());
		goToNextStep();
	}

	@Override
	public void yellowGardenerCroak() {
		goToNextStep();
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.GARDENER_CROAK;
	}

	protected void goToNextStep() {
		gameController.cancelCroak();
		gameController.setState(new GardenerCroakFrogSquare(gameController));
	}

}
