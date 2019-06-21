package game.controller.state;

import game.controller.GameController;
import game.controller.exception.move.InvallidJuniorGardenerFlowerSquareException;
import game.model.GameStatus;
import game.model.board.Square;
import game.model.flower.Flower;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;
import game.model.nenufar.NenufarSide;

public class JuniorGardenerFlowerSquare extends AbstractControllerState {

	public JuniorGardenerFlowerSquare(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void defineJuniorGardenerFlowerSquare(int row, int column) throws Exception {
		if(canMoveJuniorGardenerFlowerToSquare(row, column)) {
			moveJuniorGardenerFlowerToSquare(row, column);
			goToNextStep();
		} else {
			throw new InvallidJuniorGardenerFlowerSquareException();
		}
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.JUNIOR_GARDENER_FLOWER_SQUARE;
	}

	private boolean canMoveJuniorGardenerFlowerToSquare(int row, int column) {
		Nenufar nenufar = gameController.getCurrentBoard().getElementAtSquare(row, column);
		return (nenufar != null && nenufar.getActiveSide() == NenufarSide.DARKENED && !nenufar.isFlowered());
	}

	private void moveJuniorGardenerFlowerToSquare(int row, int column) {
		Square<Nenufar> square 	= gameController.getCurrentBoard().getSquare(row, column);
		Flower flower 			= gameController.getCurrentTurn().getSelectedJuniorGardenerFlower();
		GardenerColor color 	= gameController.getCurrentTurn().getJuniorGardenerColor();
		gameController.moveGardenerFlowerToSquare(square, flower, color);
	}

	private void goToNextStep() {
		//if(false) { //Fez pontuação
		//	//TODO
		//} else {
		gameController.setState(new SeniorGardenerFlowerSquare(gameController));
		//}
	}
	
}
