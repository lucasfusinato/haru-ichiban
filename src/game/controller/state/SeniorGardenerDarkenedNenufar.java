package game.controller.state;

import game.controller.GameController;
import game.controller.exception.move.InvallidDarkenedNenufarSquareException;
import game.model.GameStatus;
import game.model.nenufar.LightedNenufar;
import game.model.nenufar.Nenufar;

public class SeniorGardenerDarkenedNenufar extends AbstractControllerState {

	public SeniorGardenerDarkenedNenufar(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void defineDarkenedNenufar(int row, int column) throws Exception {
		if(canDefineDarkenedNenufarToSquare(row, column)) {
			defineDarkenedNenufarToSquare(row, column);
			goToNextStep();
		} else {
			throw new InvallidDarkenedNenufarSquareException();
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
		Nenufar element = gameController.getCurrentBoard().getElementAtSquare(row, column);
		element.activeBottomSide();
		gameController.setElementAtSquare(element, row, column);
	}
	
	private void goToNextStep() {
		// TODO Auto-generated method stub
		
	}

}
