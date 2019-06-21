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

	void updateScore(int score1, int score2);

	void startedRound(int round);

}
