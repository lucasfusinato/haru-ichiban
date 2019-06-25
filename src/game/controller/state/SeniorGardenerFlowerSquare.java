package game.controller.state;

import game.controller.exception.move.InvalidSeniorGardenerFlowerSquareException;
import game.model.board.Square;
import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.frog.RedFrog;
import game.model.frog.YellowFrog;
import game.model.game.GameStatus;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;

public class SeniorGardenerFlowerSquare extends AbstractPontuableState {

	public SeniorGardenerFlowerSquare(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		if(canMoveSeniorGardenerFlowerToSquare(row, column)) {
			moveSeniorGardenerFlowerToSquare(row, column);
			goToNextStep();
		} else {
			throw new InvalidSeniorGardenerFlowerSquareException();
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
	}

	
	@Override
	protected void defaultStateChange() {
		if(gameController.hasCurrentFrog()) {
			gameController.setState(new SeniorGardenerFrogSquare(gameController));
		} else {
			gameController.setState(new JuniorGardenerHaruIchiban(gameController));
		}
	}

}
