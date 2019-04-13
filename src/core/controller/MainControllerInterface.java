package core.controller;

public interface MainControllerInterface {

	void attach(MainControllerObserver observer);

	void selectMenuOption(MainMenuOption mainMenuOption);

	void exitSystem();

	void showSystemInformation();

	void startNewGame();
	
	String getSystemTitle();

	Object getSystemInformation();

}
