package game.controller.state;

import game.controller.exception.move.InvalidDarkenedNenufarSquareException;
import game.model.Element;
import game.model.frog.Frog;
import game.model.frog.RedFrog;
import game.model.frog.YellowFrog;
import game.model.game.GameStatus;
import game.model.nenufar.LightedNenufar;
import game.model.nenufar.Nenufar;

public class SeniorGardenerDarkenedNenufar extends AbstractEndFlowState {

	public SeniorGardenerDarkenedNenufar(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public void defineDarkenedNenufar(int row, int column) throws Exception {
		if(canDefineDarkenedNenufarToSquare(row, column)) {
			defineDarkenedNenufarToSquare(row, column);
			goToNextStep();
		} else {
			throw new InvalidDarkenedNenufarSquareException();
		}
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.SENIOR_GARDENER_DARKENED_NENUFAR;
	}

	private boolean canDefineDarkenedNenufarToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		return nenufar != null && nenufar.getClass() == LightedNenufar.class && nenufar.getElement() == null;
	}
	
	private void defineDarkenedNenufarToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		Element element = nenufar.getElement();
		if(element != null && (element.getClass() == RedFrog.class || element.getClass() == YellowFrog.class)) {
			gameController.setCurrentFrog((Frog) element);
		}
		nenufar.activeBottomSide();
		gameController.setElementAtSquare(nenufar, row, column);
	}
	
	private void goToNextStep() {
		if(gameController.hasCurrentFrog()) {
			gameController.setState(new SeniorGardenerDarkenedFrogSquare(gameController));
		} else {
			super.endFlow();
		}
	}

}
