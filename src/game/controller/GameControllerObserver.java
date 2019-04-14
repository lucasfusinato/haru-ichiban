package game.controller;

public interface GameControllerObserver {

	void gameWasBeStarted();

	void redFlowerWasBeSelected(int number);

	void yellowFlowerWasBeSelected(int number);

	void updateGameBoard();

}
