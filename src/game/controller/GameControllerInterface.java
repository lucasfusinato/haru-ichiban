package game.controller;

import game.model.GameStatus;

public interface GameControllerInterface {

	void attach(GameControllerObserver observer);

	void startGame(String redPlayer, String yellowPlayer, int configuration) throws Exception;

	void selectRedFlower(int index) throws Exception;

	void selectYellowFlower(int index) throws Exception;

	void defineJuniorGardenerFlowerSquare(int row, int column) throws Exception;

	void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception;

	void executeHaruIchiban(int row, int column) throws Exception;

	void defineDarkenedNenufar(int row, int column) throws Exception;

	String getGameStatusDescription();

	int getBoardRowCount();

	int getBoardColumnCount();

	boolean hasBoardElementAt(int rowIndex, int columnIndex);

	String getBoardElementAt(int rowIndex, int columnIndex);

	boolean hasBoardInfoAt(int rowIndex, int columnIndex);

	String getBoardInfoAt(int rowIndex, int columnIndex);

	int getAvailableRedFlowerQuantity();

	String getRedFlowerElementAt(int rowIndex);

	int getAvailableYellowFlowerQuantity();

	String getYellowFlowerElementAt(int index);

	boolean hasRedFlowerElementAt(int index);

	boolean canWithdrawRedFlowerAt(int index);

	boolean hasYellowFlowerElementAt(int index);

	boolean canWithdrawYellowFlowerAt(int index);

	boolean canSelectRedFlower();

	boolean canSelectYellowFlower();

	void requestRedFlowerWithdraw();

	void requestYellowFlowerWithdraw();

	boolean hasWithdrawYellowFlowerAt(int index);

	int getWithdrawYellowFlowerNumberAt(int index);

	int getWithdrawYellowFlowerQuantity();

	int getWithdrawRedFlowerQuantity();

	int getWithdrawRedFlowerNumberAt(int index);

	boolean hasWithdrawRedFlowerAt(int index);

	void withdrawRedFlower(int index) throws Exception;

	void withdrawYellowFlowerAt(int index) throws Exception;

	GameStatus getTurnStatus();

	String getWithdrawRedFlowerAt(int index);

	String getWithdrawYellowFlowerAt(int index);

	void showRedFlowerNumber(int index);

	boolean isVisibleRedFlowerNumber(int index);
	
	int getVisibleRedFlowerNumber();

	void hideRedFlowerNumber();

	void showYellowFlowerNumber(int index);

	void hideYellowFlowerNumber();

	boolean isVisibleYellowFlowerNumber(int index);

	int getVisibleYellowFlowerNumber();

	boolean hasSelectedYellowFlower();

	boolean isSelectedYellowFlower(int rowIndex);

	boolean hasSelectedRedFlower();
	
	boolean isSelectedRedFlower(int rowIndex);

	int getRoundQuantity();

	void defineSeniorGardenerFrogSquare(int row, int column) throws Exception;

}
