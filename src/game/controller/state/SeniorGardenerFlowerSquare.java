package game.controller.state;

import game.controller.GameController;
import game.controller.exception.move.InvallidSeniorGardenerFlowerSquareException;
import game.model.GameStatus;
import game.model.board.Square;
import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.frog.RedFrog;
import game.model.frog.YellowFrog;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;

public class SeniorGardenerFlowerSquare extends AbstractControllerState {

	public SeniorGardenerFlowerSquare(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		if(canMoveSeniorGardenerFlowerToSquare(row, column)) {
			moveSeniorGardenerFlowerToSquare(row, column);
		} else {
			throw new InvallidSeniorGardenerFlowerSquareException();
		}
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.SENIOR_GARDENER_FLOWER_SQUARE;
	}

	private boolean canMoveSeniorGardenerFlowerToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		return (nenufar != null && !nenufar.isFlowered());
	}
	
	private void moveSeniorGardenerFlowerToSquare(int row, int column) {
		Square<Nenufar> square 	= gameController.getCurrentBoard().getSquare(row, column);
		Nenufar lastElement		= square.getElement();
		if(lastElement != null && lastElement.getElement() != null && (lastElement.getElement().getClass() == RedFrog.class || lastElement.getElement().getClass() == YellowFrog.class)) {
			gameController.setCurrentFrog((Frog) lastElement.getElement());
		} else {
			gameController.setCurrentFrog(null);
		}
		Flower flower 			= gameController.getCurrentTurn().getSelectedSeniorGardenerFlower();
		GardenerColor color 	= gameController.getCurrentTurn().getSeniorGardenerColor();
		gameController.moveGardenerFlowerToSquare(square, flower, color);
		goToNextStep();
	}

	private void goToNextStep() {
		//TODO
		if(gameController.hasCurrentFrog()) {
			gameController.setState(new SeniorGardenerFrogSquare(gameController));
		} else {
			goToNextStep();
		}
	}

}
