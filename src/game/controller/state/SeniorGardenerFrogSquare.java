package game.controller.state;

import game.controller.GameController;
import game.controller.exception.move.InvallidSeniorGardenerFrogSquareException;
import game.model.GameStatus;
import game.model.nenufar.Nenufar;
import game.model.nenufar.NenufarSide;

public class SeniorGardenerFrogSquare extends AbstractControllerState {

	public SeniorGardenerFrogSquare(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void defineSeniorGardenerFrogSquare(int row, int column) throws Exception {
		if(canMoveSeniorGardenerFrogToSquare(row, column)) {
			moveSeniorGardenerFrogToSquare(row, column);
			gameController.setState(new JuniorGardenerHaruIchiban(gameController));
		} else {
			throw new InvallidSeniorGardenerFrogSquareException();
		}
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.SENIOR_GARDENER_FROG_SQUARE;
	}

	private boolean canMoveSeniorGardenerFrogToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		return (nenufar != null && nenufar.getElement() == null && nenufar.getActiveSide() == NenufarSide.LIGHTED);
	}
	
	private void moveSeniorGardenerFrogToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		nenufar.setElement(gameController.getCurrentFrog());
		gameController.setElementAtSquare(nenufar, row, column);
	}

}
