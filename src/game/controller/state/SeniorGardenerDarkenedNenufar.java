package game.controller.state;

import game.controller.exception.move.InvalidDarkenedNenufarSquareException;
import game.model.board.Square;
import game.model.frog.Frog;
import game.model.game.GameStatus;
import game.model.nenufar.Nenufar;
import game.model.strategy.square.CompareFlowerNenufarStrategy;
import game.model.strategy.square.CompareRedFrogNenufarStrategy;
import game.model.strategy.square.CompareYellowFrogNenufarStrategy;

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
		Square<Nenufar> square = gameController.getCurrentBoard().getSquare(row, column);
		return !(new CompareFlowerNenufarStrategy()).compare(square);
	}
	
	private void defineDarkenedNenufarToSquare(int row, int column) {
		Square<Nenufar> square = gameController.getCurrentBoard().getSquare(row, column);
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		if((new CompareRedFrogNenufarStrategy()).compare(square) || (new CompareYellowFrogNenufarStrategy()).compare(square)) {
			gameController.setCurrentFrog((Frog) nenufar.getElement());
			nenufar.setElement(null);
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
