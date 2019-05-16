package core.controller;

import java.util.List;

public interface MainControllerInterface {

	void attach(MainControllerObserver observer);

	void detach(MainControllerObserver observer);

	void startNewGame();

	void exitSystem();

	void startGame();
	
	void definePlayer1(String playerName);
	
	void definePlayer2(String playerName);
	
	void defineLevel(Integer level);
	
	void showGameInformation();
	
	String getSystemTitle();

	String getSystemInformation();

	List<String> getLevelOptions();


}
