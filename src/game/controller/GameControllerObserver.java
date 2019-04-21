package game.controller;

public interface GameControllerObserver {
	
	void gameHasStarted();
	void gameBoardHasBeenUpdated();
	void gameStatusHasBeenUpdated(String gameStatus);
	void scoringHasBeenUpdated(int score1, int score2);
	void redFlowerHasBeenSelected(int number);
	void redFlowersHasBeenUpdated();
	void yellowFlowerHasBeenSelected(int number);
	void yellowFlowersHasBeenUpdated();
	void gameHasEnded(String name);
	void gameHasEnded();
	void showInvalidMoveError(String message);
	
}
