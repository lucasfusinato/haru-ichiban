package game.controller.state;

import game.controller.GameController;
import game.controller.exception.move.AlreadySelectedRedFlowerException;
import game.controller.exception.move.AlreadySelectedYellowFlowerException;
import game.model.GameStatus;
import game.model.gardener.Gardener;

public class FlowerSelection extends AbstractControllerState {

	public FlowerSelection(GameController gameController) {
		super(gameController);
	}
	
	@Override
	public void selectRedFlower(int index) throws Exception {
		if(!gameController.hasTurnSelectedRedFlower()) {
			gameController.setTurnSelectedRedFlower(index);
			if(gameController.hasTurnSelectedYellowFlower()) {
				defineGardeners();
			}
		} else {
			throw new AlreadySelectedRedFlowerException();
		}
	}

	@Override
	public void selectYellowFlower(int index) throws Exception {
		if(!gameController.hasTurnSelectedYellowFlower()) {
			gameController.setTurnSelectedYellowFlower(index);
			if(gameController.hasTurnSelectedRedFlower()) {
				defineGardeners();
			}
		} else {
			throw new AlreadySelectedYellowFlowerException();
		}
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.FLOWER_SELECTION;
	}

	private void defineGardeners() {
		int redNumber = gameController.getTurnSelectedRedNumber();
		int yellowNumber = gameController.getTurnSelectedYellowNumber();
		if(redNumber > yellowNumber) {
			defineGardeners(gameController.getRedGardener(), gameController.getYellowGardener());
		} else if(redNumber < yellowNumber) {
			defineGardeners(gameController.getYellowGardener(), gameController.getRedGardener());
		} else {
			gameController.setState(new GardenerCroak(gameController));
		}
	}

	private void defineGardeners(Gardener seniorGardener, Gardener yellowGardener) {
		gameController.updateTurnGardeners(seniorGardener, yellowGardener);
		gameController.setState(new JuniorGardenerFlowerSquare(gameController));
	}

}
