package game.controller;

import game.model.gardener.GardenerColor;

public interface GameControllerInterface {

	void startGame();
	void attach(GameControllerObserver observer);
	int getBoardRowCount();
	int getBoardColumnCount();
	Object getBoardElementImageAt(int rowIndex, int columnIndex);
	void mouseClickedInBoardSquare(int selectedRow, int selectedColumn);
	void mouseMovedToBoardSquare(int rowIndex, int columnIndex);
	int getAvailableFlowerQuantity(GardenerColor flowerColor);
	Object getFlowerImageAt(GardenerColor flowerColor, int index);
	String getGameStatus();
	void mouseMovedToFlower(GardenerColor gardenerColor, int index);
	void mouseExitedFlowers(GardenerColor gardenerColor);
	void mouseClickedInFlower(GardenerColor gardenerColor, int selectedRow);
	String getStatusImage();

}
