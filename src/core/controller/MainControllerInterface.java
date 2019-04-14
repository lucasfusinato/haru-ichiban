package core.controller;

import game.controller.GameControllerInterface;

public interface MainControllerInterface {

	void attach(MainControllerObserver observer);

	void selectMenuOption(MainMenuOption mainMenuOption);

	void exitSystem();

	void showSystemInformation();

	void startNewGame();
	
	String getSystemTitle();

	Object getSystemInformation();

	GameControllerInterface getGameController();

}
