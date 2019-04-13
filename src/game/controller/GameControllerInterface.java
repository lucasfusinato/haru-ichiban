package game.controller;

public interface GameControllerInterface {
	
	void attach(GameControllerObserver observer);
	void startGame();
	int getBoardRows();
	int getBoardCols();
	String getBoardElementAt(int rowIndex, int columnIndex);
	String getGameTitle();

}
