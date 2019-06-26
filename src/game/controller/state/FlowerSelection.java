package game.controller.state;


import game.controller.exception.move.AlreadySelectedRedFlowerException;
import game.controller.exception.move.AlreadySelectedYellowFlowerException;
import game.model.game.GameStatus;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerItem;

public class FlowerSelection extends AbstractControllerState {

	public FlowerSelection(GameControllerStateAccess gameController) {
		this(gameController, false);
	}
	
	public FlowerSelection(GameControllerStateAccess gameController, boolean newTurn) {
		super(gameController);
		if(newTurn) {
			gameController.goToNextTurn();
		}
		removeGardenersItems();
		gameController.blockItems(false);
	}

	private void removeGardenersItems() {
		gameController.removeRedGardenerItems();
		gameController.removeYellowGardenerItems();
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
	public void equiparYellowGardener(int item) {
		gameController.addYellowGardenerItem(GardenerItem.getByNumber(item));
	}
	
	@Override
	public void equiparRedGardener(int item) {
		gameController.addRedGardenerItem(GardenerItem.getByNumber(item));
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.FLOWER_SELECTION;
	}

	private void defineGardeners() {
		gameController.blockItems(true);
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
