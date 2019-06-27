package game.controller.state;

import game.model.game.GameStatus;
import game.model.gardener.Gardener;

public class FinishedGame extends AbstractControllerState {

	public FinishedGame(GameControllerStateAccess gameController) {
		super(gameController);
	}
	
	@Override
	public boolean isFinished() {
		return true;
	}
	
	@Override
	public String getWinnerGardener() {
		int redPoints = gameController.getRedPoints();
		int yellowPoints = gameController.getYellowPoints();
		Gardener winner =  (redPoints > yellowPoints ? gameController.getRedGardener() : (yellowPoints > redPoints ? gameController.getYellowGardener() : null));
		return winner != null ? winner.getName() : null;
	}

	@Override
	public GameStatus getStatus() {
		return GameStatus.FINISHED_GAME;
	}

}
