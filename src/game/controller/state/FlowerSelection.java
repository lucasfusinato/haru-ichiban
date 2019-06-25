package game.controller.state;

import game.controller.exception.move.AlreadySelectedRedFlowerException;
import game.controller.exception.move.AlreadySelectedYellowFlowerException;
import game.model.game.GameStatus;
import game.model.gardener.Gardener;

public class FlowerSelection extends AbstractControllerState {

	public FlowerSelection(GameControllerStateAccess gameController) {
		this(gameController, false);
	}
	
	public FlowerSelection(GameControllerStateAccess gameController, boolean newTurn) {
		super(gameController);
		if(newTurn) {
			gameController.goToNextTurn();
		}
	}

	@Override
	public void selectRedFlower(int index) throws Exception {
		if(canSelectRedFlower()) {
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
		if(canSelectYellowFlower()) {
			gameController.setTurnSelectedYellowFlower(index);
			if(gameController.hasTurnSelectedRedFlower()) {
				defineGardeners();
			}
		} else {
			throw new AlreadySelectedYellowFlowerException();
		}
	}
	
	@Override
	public boolean canSelectRedFlower() {
		return !gameController.hasTurnSelectedRedFlower();
	}
	
	@Override
	public boolean canSelectYellowFlower() {
		return !gameController.hasTurnSelectedYellowFlower();
	}

	@Override
	public void showRedFlowerNumber(int index) {
		if(canSelectRedFlower()) {
			gameController.setVisibleRedFlowerNumber(gameController.getCurrentTurn().getRedFlower(index));
		}
	}

	@Override
	public void showYellowFlowerNumber(int index) {
		if(canSelectRedFlower()) {
			gameController.setVisibleYellowFlowerNumber(gameController.getCurrentTurn().getYellowFlower(index));
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
