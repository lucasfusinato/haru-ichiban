package game.controller.state;

import game.model.Element;
import game.model.board.Board;
import game.model.board.Square;
import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.game.Game;
import game.model.game.Round;
import game.model.game.Turn;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;

public interface GameControllerStateAccess {

	Board<Nenufar> getCurrentBoard();

	void setCurrentFrog(Frog redFrog);

	void requestMoveRedFrog();

	void requestMoveYellowFrog();

	Element getCurrentFrog();

	void setElementAtSquare(Nenufar nenufar, int row, int column);

	void moveGardenerFlowerToSquare(Square<Nenufar> square, Flower flower, GardenerColor color);

	Turn getCurrentTurn();

	void setCroakGardener(Gardener gardener);

	void goToNextTurn();

	Flower removeRoundWithdrawRedFlower(int index);

	void addTurnRedFlower(Flower flower);

	boolean checkWithdrawedRedFlowers();

	boolean checkWithdrawedYellowFlowers();

	Flower removeRoundWithdrawYellowFlower(int index);

	void addTurnYellowFlower(Flower flower);

	void setState(AbstractControllerState gardenerCroak);

	void setTurnSelectedRedFlower(int index);

	boolean hasTurnSelectedYellowFlower();

	void setTurnSelectedYellowFlower(int index);

	boolean hasTurnSelectedRedFlower();

	void setVisibleRedFlowerNumber(Flower redFlower);

	void setVisibleYellowFlowerNumber(Flower yellowFlower);

	int getTurnSelectedRedNumber();

	int getTurnSelectedYellowNumber();

	Gardener getRedGardener();

	Gardener getYellowGardener();

	void requestCroak();

	void updateTurnGardeners(Gardener seniorGardener, Gardener yellowGardener);

	void goToNextRound();

	int getRedPoints();

	int getYellowPoints();

	void addScore(int redPoints, int yellowPoints);

	int getRoundQuantity();

	Round getCurrentRound();

	void setGame(Game<Nenufar> createGame);

	boolean hasSelectedSquare();

	Square<Nenufar> getSelectedSquare();

	void setSelectedSquare(Square<Nenufar> square);

	boolean hasCurrentFrog();

	void cancelCroak();

	Gardener getCroakGardener();

}
