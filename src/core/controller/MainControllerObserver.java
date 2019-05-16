package core.controller;

import game.controller.GameControllerInterface;

public interface MainControllerObserver {

	void systemWillBeClosed();

	void requestGameInformation();

	void showGameInformation(String gameInformation);

	void updatePlayer1(String player);

	void updatePlayer2(String player);

	void updateLevel(Integer level);

	void gameStarted(GameControllerInterface gameController);

}
