package game.controller.state;

import game.controller.exception.move.InvalidGardenerFrogSquareException;
import game.model.game.GameStatus;
import game.model.nenufar.Nenufar;
import game.model.nenufar.NenufarSide;

public class SeniorGardenerDarkenedFrogSquare extends AbstractEndFlowState {

	public SeniorGardenerDarkenedFrogSquare(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public void defineGardenerFrogSquare(int row, int column) throws Exception {
		if(canMoveFrogToSquare(row, column)) {
			moveFrogToSquare(row, column);
			endFlow();
		} else {
			throw new InvalidGardenerFrogSquareException();
		}
	}
	
	@Override
	public GameStatus getStatus() {
		return GameStatus.SENIOR_GARDENER_DARKENED_FROG_NENUFAR;
	}

	private boolean canMoveFrogToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		return (nenufar != null && nenufar.getElement() == null && nenufar.getActiveSide() == NenufarSide.LIGHTED);
	}
	
	private void moveFrogToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		nenufar.setElement(gameController.getCurrentFrog());
		gameController.setElementAtSquare(nenufar, row, column);
		gameController.setCurrentFrog(null);
	}

}
