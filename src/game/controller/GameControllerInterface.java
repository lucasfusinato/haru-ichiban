package game.controller;

public interface GameControllerInterface {
	
	void attach(GameControllerObserver observer);
	void startGame();
	int getBoardRows();
	int getBoardCols();
	String getBoardElementAt(int rowIndex, int columnIndex);
	String getGameTitle();
	int getRedNenufarRowCount();
	int getRedNenufarColumnCount();
	String getRedNenufarAt(int rowIndex, int columnIndex);
	int getYellowNenufarRowCount();
	int getYellowNenufarColumnCount();
	String getYellowNenufarAt(int rowIndex, int columnIndex);
	void selectRedFlower(int selectedRow, int selectedColumn);
	void selectYellowFlower(int selectedRow, int selectedColumn);
	void selectSquare(int selectedRow, int selectedColumn);

}
