package game.controller;

public interface GameControllerObserver {

	void updateRedFlowers();

	void updateYellowFlowers();

	void updateGardeners(String seniorGardener, String juniorGardener);

	void updateStatus(String status);

	void updateBoard();

	void requestRedFlowerWithdraw();

	void requestYellowFlowerWithdraw();

	void updateWithdrawRedFlowers();

	void updateWithdrawYellowFlowers();

	void withdrawedRedFlowers();

	void withdrawedYellowFlowers();

	void updateScore(int score1, int score2, int addedScore2, int score22);

	void startedRound(int round);

	void showCroakButton();

	void hideCroakButton();

	void requestMoveRedFrog();

	void requestMoveYellowFrog();

	void updateRedItems();

	void updateYellowItems();

	void redPlayerUsedItem(String item);

	void yellowPlayerUsedItem(String item);

	void blockItems(boolean b);

	void updateWinner(String winner);

}
