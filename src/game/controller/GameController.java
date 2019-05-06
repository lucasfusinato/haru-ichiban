package game.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.controller.builder.NenufarBoardBuilder;
import game.controller.builder.NenufarBoardDirector;
import game.controller.builder.StartNenufarBoardBuilder;
import game.controller.exception.InvallidMoveException;
import game.controller.exception.InvallidMoveTimeException;
import game.model.Direction;
import game.model.Game;
import game.model.TurnStatus;
import game.model.NenufarBoard;
import game.model.Player;
import game.model.Round;
import game.model.Square;
import game.model.Turn;
import game.model.factory.AbstractGardenerFactory;
import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.flower.Flower;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;
import game.model.nenufar.NenufarSide;
import game.model.nenufar.LightedNenufar;
import utils.Director;

public class GameController implements GameControllerInterface {
	
	private static final int AVAILABLE_FLOWER_QUANTITY = 3;
	private static final int TURNS_PER_ROUND = 8;
	private static GameController instance;
	private List<GameControllerObserver> observers;
	private List<Game> games;
	private Game currentGame;
	private Round currentRound;
	private NenufarBoard currentBoard;
	private Turn currentTurn;
	private Square<Nenufar> selectedSquare;
	private Flower visibleRedFlowerNumber;
	private Flower visibleYellowFlowerNumber;
	
	public static synchronized GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	private GameController() {
		observers = new ArrayList<>();
		games = new ArrayList<>();
		currentGame = null;
		currentRound = null;
		currentBoard = null;
		currentTurn = null;
		visibleRedFlowerNumber = null;
		visibleYellowFlowerNumber = null;
	}

	@Override
	public void attach(GameControllerObserver observer) {
		observers.add(observer);
	}
	
	@Override
	public void startGame(Player red, Player yellow) {
		currentGame = new Game(red, yellow);
		games.add(currentGame);
		goToNextRound();
	}
	
	@Override
	public void selectRedFlower(int index) throws InvallidMoveTimeException {
		if(canSelectRedFlower()) {
			Flower selectedFlower = currentTurn.getRedFlower(index);
			currentTurn.setSelectedRedFlower(selectedFlower);
			notifyUpdatedRedFlowers();
			defineGardeners();
		} else {
			throw new InvallidMoveTimeException();
		}
	}
	
	@Override
	public void selectYellowFlower(int index) throws InvallidMoveTimeException {
		if(canSelectYellowFlower()) {
			Flower selectedFlower = currentTurn.getYellowFlower(index);
			currentTurn.setSelectedYellowFlower(selectedFlower);
			notifyUpdatedYellowFlowers();
			defineGardeners();
		} else {
			throw new InvallidMoveTimeException();
		}
	}

	@Override
	public void defineJuniorGardenerFlowerSquare(int row, int column) throws InvallidMoveTimeException, InvallidMoveException {
		if(canSelectJuniorGardenerFlowerSquare()) {
			if(canMoveJuniorGardenerFlowerToSquare(row, column)) {
				moveJuniorGardenerFlowerToSquare(row, column);
				goToNextStep();
			} else {
				throw new InvallidMoveException();
			}
		} else {
			throw new InvallidMoveTimeException();
		}
	}

	@Override
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws InvallidMoveTimeException, InvallidMoveException {
		if(canSelectSeniorGardenerFlowerSquare()) {
			if(canMoveSeniorGardenerFlowerToSquare(row, column)) {
				moveSeniorGardenerFlowerToSquare(row, column);
				goToNextStep();
			} else {
				throw new InvallidMoveException();
			}
		} else {
			throw new InvallidMoveTimeException();
		}
	}
	
	@Override
	public void executeHaruIchiban(int row, int column) throws InvallidMoveTimeException {
		if(canExecuteHaruIchiban()) {
			if(hasSelectedSquareToHaruIchiban()) {
				callHaruIchiban(row, column);
			} else {
				selectSquareToHaruIchiban(row, column);
			}
		} else {
			throw new InvallidMoveTimeException();
		}
	}
	
	@Override
	public void defineDarkenedNenufar(int row, int column) throws InvallidMoveTimeException, InvallidMoveException {
		if(canDefineDarkenedNenufar()) {
			if(canDefineDarkenedNenufarToSquare(row, column)) {
				defineDarkenedNenufarToSquare(row, column);
				goToNextStep();
			} else {
				throw new InvallidMoveException();
			}
		} else {
			throw new InvallidMoveTimeException();
		}
	}

	@Override
	public String getGameStatusDescription() {
		return (hasCurrentGame()) ? currentTurn.getStatus().getDescricao() : null;
	}

	@Override
	public int getBoardRowCount() {
		return (hasCurrentGame()) ? currentBoard.getRows() : 0;
	}

	@Override
	public int getBoardColumnCount() {
		return (hasCurrentGame()) ? currentBoard.getCols() : 0;
	}

	@Override
	public boolean hasBoardElementAt(int rowIndex, int columnIndex) {
		return (hasCurrentGame()) ? currentBoard.getElementAtSquare(rowIndex, columnIndex) != null : false;
	}

	@Override
	public String getBoardElementAt(int rowIndex, int columnIndex) {
		Nenufar nenufar = currentBoard.getElementAtSquare(rowIndex, columnIndex); 
		String element = nenufar.getDescription();
		if(nenufar.getElement() != null) {
			element += "-" + nenufar.getElement().getDescription();
		}
		return element;
	}

	@Override
	public boolean hasBoardInfoAt(int rowIndex, int columnIndex) {
		return hasCurrentGame() && isCurrentTurnStatus(TurnStatus.JUNIOR_GARDENER_HARU_ICHIBAN) && getHaruIchibanDirection(rowIndex, columnIndex) != null;
	}

	@Override
	public String getBoardInfoAt(int rowIndex, int columnIndex) {
		return getHaruIchibanDirection(rowIndex, columnIndex).getDescription();
	}

	@Override
	public int getAvailableRedFlowerQuantity() {
		return AVAILABLE_FLOWER_QUANTITY;
	}

	@Override
	public int getAvailableYellowFlowerQuantity() {
		return AVAILABLE_FLOWER_QUANTITY;
	}

	@Override
	public String getRedFlowerElementAt(int rowIndex) {
		return currentTurn.getRedFlowerAt(rowIndex).getDescription();
	}

	@Override
	public boolean isVisibleRedFlowerNumber(int index) {
		return visibleRedFlowerNumber != null && visibleRedFlowerNumber == currentTurn.getRedFlower(index);
	}
	
	@Override
	public int getVisibleRedFlowerNumber() {
		return visibleRedFlowerNumber.getNumber();
	}

	@Override
	public boolean isVisibleYellowFlowerNumber(int index) {
		return visibleYellowFlowerNumber != null && visibleYellowFlowerNumber == currentTurn.getYellowFlower(index);
	}
	
	@Override
	public int getVisibleYellowFlowerNumber() {
		return visibleYellowFlowerNumber.getNumber();
	}

	@Override
	public String getYellowFlowerElementAt(int rowIndex) {
		return currentTurn.getYellowFlower(rowIndex).getDescription();
	}

	@Override
	public boolean hasRedFlowerElementAt(int index) {
		return hasCurrentGame() && currentTurn.getRedFlowers().size() > index;
	}

	@Override
	public boolean hasYellowFlowerElementAt(int index) {
		return hasCurrentGame() && currentTurn.getYellowFlowers().size() > index;
	}

	@Override
	public boolean canWithdrawRedFlowerAt(int index) {
		return canWithdrawRedFlowerAt(index, true);
	}

	@Override
	public boolean canWithdrawYellowFlowerAt(int index) {
		return canWithdrawYellowFlowerAt(index, true);
	}

	@Override
	public boolean canSelectRedFlower() {
		return isFlowerSelectionStatus() && !currentTurn.hasSelectedRedFlower();
	}

	@Override
	public boolean canSelectYellowFlower() {
		return isFlowerSelectionStatus() && !currentTurn.hasSelectedYellowFlower();
	}

	@Override
	public void requestRedFlowerWithdraw() {
		for(GameControllerObserver observer : observers) {
			observer.requestRedFlowerWithdraw();
		}
	}

	@Override
	public void requestYellowFlowerWithdraw() {
		for(GameControllerObserver observer : observers) {
			observer.requestYellowFlowerWithdraw();
		}
	}

	@Override
	public String getWithdrawYellowFlowerAt(int index) {
		return currentRound.getWithdrawYellowFlower(index).getDescription();
	}

	@Override
	public String getWithdrawRedFlowerAt(int index) {
		return currentRound.getWithdrawRedFlower(index).getDescription();
	}

	@Override
	public int getWithdrawYellowFlowerNumberAt(int index) {
		return currentRound.getWithdrawYellowFlower(index).getNumber();
	}

	@Override
	public int getWithdrawRedFlowerNumberAt(int index) {
		return currentRound.getWithdrawRedFlower(index).getNumber();
	}

	@Override
	public int getWithdrawYellowFlowerQuantity() {
		return (hasCurrentGame()) ? currentRound.getWithdrawYellowFlowers().size() : 0;
	}

	@Override
	public int getWithdrawRedFlowerQuantity() {
		return (hasCurrentGame()) ? currentRound.getWithdrawRedFlowers().size() : 0;
	}

	@Override
	public boolean hasWithdrawYellowFlowerAt(int index) {
		return hasCurrentGame() && currentRound.getWithdrawYellowFlowers().size() > index;
	}

	@Override
	public boolean hasWithdrawRedFlowerAt(int index) {
		return hasCurrentGame() && currentRound.getWithdrawRedFlowers().size() > index;
	}

	@Override
	public void withdrawRedFlower(int index) throws InvallidMoveTimeException {
		if(isWithdrawFlowerStatus()) {
			Flower flower = currentRound.removeWithdrawRedFlower(index);
			currentTurn.addRedFlower(flower);
			notifyUpdatedRedFlowers();
			notifyUpdatedWithdrawRedFlowers();
			if(withdrawedRedFlowers()) {
				notifyWithdrawedRedFlowers();
				if(withdrawedYellowFlowers()) {
					goToNextStep();
				}
			}
		} else {
			throw new InvallidMoveTimeException();
		}
	}

	@Override
	public void withdrawYellowFlowerAt(int index) throws InvallidMoveTimeException {
		if(isWithdrawFlowerStatus()) {
			Flower flower = currentRound.removeWithdrawYellowFlower(index);
			currentTurn.addYellowFlower(flower);
			notifyUpdatedYellowFlowers();
			notifyUpdatedWithdrawYellowFlowers();
			if(withdrawedYellowFlowers()) {
				notifyWithdrawedYellowFlowers();
				if(withdrawedRedFlowers()) {
					goToNextStep();
				}
			}
		} else {
			throw new InvallidMoveTimeException();
		}
	}

	@Override
	public TurnStatus getTurnStatus() {
		return currentTurn.getStatus();
	}

	@Override
	public void showRedFlowerNumber(int index) {
		if(canSelectRedFlower()) {
			visibleRedFlowerNumber = currentTurn.getRedFlower(index);
			notifyUpdatedRedFlowers();
		}
	}

	@Override
	public void hideRedFlowerNumber() {
		visibleRedFlowerNumber = null;
		notifyUpdatedRedFlowers();
	}

	@Override
	public void showYellowFlowerNumber(int index) {
		if(canSelectYellowFlower()) {
			visibleYellowFlowerNumber = currentTurn.getYellowFlower(index);
			notifyUpdatedYellowFlowers();
		}
	}

	@Override
	public void hideYellowFlowerNumber() {
		visibleYellowFlowerNumber = null;
		notifyUpdatedYellowFlowers();
	}

	@Override
	public boolean hasSelectedYellowFlower() {
		return hasCurrentGame() && currentTurn.hasSelectedYellowFlower();
	}
	
	@Override
	public boolean hasSelectedRedFlower() {
		return hasCurrentGame() && currentTurn.hasSelectedRedFlower();
	}

	@Override
	public boolean isSelectedYellowFlower(int rowIndex) {
		return currentTurn.getSelectedYellowFlower() == currentTurn.getYellowFlower(rowIndex);
	}

	@Override
	public boolean isSelectedRedFlower(int rowIndex) {
		return currentTurn.getSelectedRedFlower() == currentTurn.getRedFlower(rowIndex);
	}
	
	private boolean canWithdrawRedFlowerAt(int index, boolean checkStatus) {
		return (!checkStatus || isWithdrawFlowerStatus()) && currentTurn.getRedFlowers().size() <= index && TURNS_PER_ROUND - currentRound.getTurns().size() > index;
	}
	
	private boolean canWithdrawYellowFlowerAt(int index, boolean checkStatus) {
		return (!checkStatus || isWithdrawFlowerStatus()) && currentTurn.getYellowFlowers().size() <= index && TURNS_PER_ROUND - currentRound.getTurns().size() > index;
	}

	private void goToNextRound() {
		currentBoard = createStartBoard();
		currentRound = createRound(currentBoard);
		currentGame.addRound(currentRound);
		notifyUpdatedBoard();
		goToNextTurn();
	}

	private Round createRound(NenufarBoard board) {
		return new Round(board, createRoundRedFlowers(), createRoundYellowFlowers());
	}

	private List<Flower> createRoundRedFlowers() {
		return createRoundFlowers(RedGardenerFactory.getInstance());
	}

	private List<Flower> createRoundYellowFlowers() {
		return createRoundFlowers(YellowGardenerFactory.getInstance());
	}

	private List<Flower> createRoundFlowers(AbstractGardenerFactory factory) {
		List<Flower> flowers = new ArrayList<>();
		for(int i = 1; i <= TURNS_PER_ROUND; i++) {
			flowers.add(factory.createFlower(i));
		}
		Collections.shuffle(flowers);
		return flowers;
	}

	private void goToNextTurn() {
		Turn olderTurn = currentTurn;
		currentTurn = new Turn();
		if(olderTurn != null) {
			currentTurn.setRedFlowers(olderTurn.getRedFlowers());
			currentTurn.setYelllowFlowers(olderTurn.getYellowFlowers());
		}
		currentRound.addTurn(currentTurn);
		notifyUpdatedRedFlowers();
		notifyUpdatedYellowFlowers();
		defineInitialTurnStatus();
	}

	private void defineInitialTurnStatus() {
		if(canWithdrawRedFlower() || canWithdrawYellowFlower()) {
			updateTurnStatus(TurnStatus.WITHDRAW_FLOWER);
		} else {
			updateTurnStatus(TurnStatus.FLOWER_SELECTION);
		}
	}

	private boolean canWithdrawYellowFlower() {
		for(int i = 0; i < getAvailableYellowFlowerQuantity(); i++) {
			if(canWithdrawYellowFlowerAt(i, false)) {
				return true;
			}
		}
		return false;
	}

	private boolean canWithdrawRedFlower() {
		for(int i = 0; i < getAvailableRedFlowerQuantity(); i++) {
			if(canWithdrawRedFlowerAt(i, false)) {
				return true;
			}
		}
		return false;
	}

	private void restartTurn() {
		currentRound.removeTurn(currentTurn);
		goToNextTurn();
	}

	private void goToNextStep() {
		switch(currentTurn.getStatus()) {
			case WITHDRAW_FLOWER:
				updateTurnStatus(TurnStatus.FLOWER_SELECTION);
				break;
			case FLOWER_SELECTION:
				updateTurnStatus(TurnStatus.JUNIOR_GARDENER_FLOWER_SQUARE);
				break;
			case JUNIOR_GARDENER_FLOWER_SQUARE:
				updateTurnStatus(TurnStatus.SENIOR_GARDENER_FLOWER_SQUARE);
				break;
			case SENIOR_GARDENER_FLOWER_SQUARE:
				updateTurnStatus(TurnStatus.JUNIOR_GARDENER_HARU_ICHIBAN);
				break;
			case JUNIOR_GARDENER_HARU_ICHIBAN:
				updateTurnStatus(TurnStatus.SENIOR_GARDENER_DARKENED_NENUFAR);
				break;
			case SENIOR_GARDENER_DARKENED_NENUFAR:
				goToNextTurn();
				break;
			default:
				break;
		}
	}

	private boolean isWithdrawFlowerStatus() {
		return isCurrentTurnStatus(TurnStatus.WITHDRAW_FLOWER);
	}

	private boolean isFlowerSelectionStatus() {
		return isCurrentTurnStatus(TurnStatus.FLOWER_SELECTION);
	}
	
	private boolean canSelectJuniorGardenerFlowerSquare() {
		return isCurrentTurnStatus(TurnStatus.JUNIOR_GARDENER_FLOWER_SQUARE);
	}

	private boolean canSelectSeniorGardenerFlowerSquare() {
		return isCurrentTurnStatus(TurnStatus.SENIOR_GARDENER_FLOWER_SQUARE);
	}

	private boolean canExecuteHaruIchiban() {
		return isCurrentTurnStatus(TurnStatus.JUNIOR_GARDENER_HARU_ICHIBAN);
	}

	private boolean canDefineDarkenedNenufar() {
		return isCurrentTurnStatus(TurnStatus.SENIOR_GARDENER_DARKENED_NENUFAR);
	}
	
	private boolean isCurrentTurnStatus(TurnStatus status) {
		return hasCurrentGame() && currentTurn.isStatus(status);
	}

	private boolean withdrawedRedFlowers() {
		return currentTurn.getRedFlowers().size() == AVAILABLE_FLOWER_QUANTITY;
	}

	private boolean withdrawedYellowFlowers() {
		return currentTurn.getYellowFlowers().size() == AVAILABLE_FLOWER_QUANTITY;
	}

	private boolean canMoveJuniorGardenerFlowerToSquare(int row, int column) {
		Nenufar nenufar = currentBoard.getElementAtSquare(row, column);
		return (nenufar != null && nenufar.getActiveSide() == NenufarSide.DARKENED);
	}

	private boolean canMoveSeniorGardenerFlowerToSquare(int row, int column) {
		Nenufar nenufar = currentBoard.getElementAtSquare(row, column);
		return (nenufar != null && !nenufar.isFlowered());
	}

	private void moveJuniorGardenerFlowerToSquare(int row, int column) {
		Square<Nenufar> square 	= currentBoard.getSquare(row, column);
		Flower flower 			= currentTurn.getSelectedJuniorGardenerFlower();
		GardenerColor color 	= currentTurn.getJuniorGardenerColor();
		moveGardenerFlowerToSquare(square, flower, color);
	}
	
	private void moveSeniorGardenerFlowerToSquare(int row, int column) {
		Square<Nenufar> square 	= currentBoard.getSquare(row, column);
		Flower flower 			= currentTurn.getSelectedSeniorGardenerFlower();
		GardenerColor color 	= currentTurn.getSeniorGardenerColor();
		moveGardenerFlowerToSquare(square, flower, color);
	}
	
	private void moveGardenerFlowerToSquare(Square<Nenufar> square, Flower flower, GardenerColor color) {
		currentTurn.removeFlower(flower, color);
		currentBoard.getElementAtSquare(square).activeTopSide();
		currentBoard.getElementAtSquare(square).setElement(flower);
		notifyUpdatedFlowers(color);
		notifyUpdatedBoard();
	}

	private boolean hasSelectedSquareToHaruIchiban() {
		return selectedSquare != null;
	}

	private void callHaruIchiban(int row, int column) {
		Square<Nenufar> square = currentBoard.getSquare(row, column);
		if(square.getElement() == null) {
			if(getHaruIchibanDirection(square) != null) {
				callHaruIchiban(square);
			} else {
				selectSquareToHaruIchiban(null);
			}
		} else if(square == selectedSquare) {
			selectSquareToHaruIchiban(null);
		} else {
			selectSquareToHaruIchiban(square);
		}
	}
	
	private void callHaruIchiban(Square<Nenufar> square) {
		Direction direction = getHaruIchibanDirection(square);
		if(direction != null) {
			Square<Nenufar> nextSquare;
			while(square != selectedSquare) {
				nextSquare = currentBoard.getSquare(getHaruIchibanX(direction, square.getRow()), getHaruIchibanY(direction, square.getColumn()));
				square.setElement(nextSquare.getElement());
				square = nextSquare;
			}
			selectedSquare.setElement(null);
			selectSquareToHaruIchiban(null);
			goToNextStep();
		}
	}

	private int getHaruIchibanY(Direction direction, int y) {
		switch(direction) {
			case LEFT: 	return y + 1;
			case RIGHT: return y - 1;
			default: 	return y;
		}
	}

	private int getHaruIchibanX(Direction direction, int x) {
		switch(direction) {
			case UP: 	return x + 1;
			case DOWN: 	return x - 1;
			default: 	return x;
		}
	}

	private Direction getHaruIchibanDirection(int rowIndex, int columnIndex) {
		return getHaruIchibanDirection(currentBoard.getSquare(rowIndex, columnIndex));
	}

	private Direction getHaruIchibanDirection(Square<Nenufar> square) {
		Direction direction = null;
		if(selectedSquare != null) {
			if(square != selectedSquare && square.getElement() == null) {
				if(selectedSquare.getRow() == square.getRow()) {
					if(square.getColumn() < selectedSquare.getColumn()) {
						direction = Direction.LEFT;
					} else if(square.getColumn() > selectedSquare.getColumn()) {
						direction = Direction.RIGHT;
					}
				} else if(this.selectedSquare.getColumn() == square.getColumn()) {
					if(square.getRow() < selectedSquare.getRow()) {
						direction = Direction.UP;
					} else if(square.getRow() > selectedSquare.getRow()) {
						direction = Direction.DOWN;
					}
				}
				direction = (canMoveToDirection(direction, square)) ? direction : null;
			}
		}
		return direction;
	}

	private boolean canMoveToDirection(Direction direction, Square<Nenufar> square) {
		if(direction != null) {
			Square<Nenufar> nextSquare = selectedSquare;
			while(square != selectedSquare) {
				nextSquare = currentBoard.getSquare(getHaruIchibanX(direction, square.getRow()), getHaruIchibanY(direction, square.getColumn()));
				if(nextSquare.getElement() == null) {
					return false;
				}
				square = nextSquare;
			}
			return true;
		} else {
			return false;
		}
	}

	private void selectSquareToHaruIchiban(int row, int column) {
		selectSquareToHaruIchiban(currentBoard.getSquare(row, column));
	}
	
	private void selectSquareToHaruIchiban(Square<Nenufar> square) {
		selectedSquare = square;
		notifyUpdatedBoard();
	}

	private boolean canDefineDarkenedNenufarToSquare(int row, int column) {
		Nenufar nenufar = currentBoard.getElementAtSquare(row, column);
		return nenufar != null && nenufar.getClass() == LightedNenufar.class;
	}
	
	private void defineDarkenedNenufarToSquare(int row, int column) {
		currentBoard.getElementAtSquare(row, column).activeBottomSide();
		notifyUpdatedBoard();
	}
	
	private NenufarBoard createStartBoard() {
		NenufarBoardBuilder builder = new StartNenufarBoardBuilder();
		Director director = new NenufarBoardDirector(builder);
		director.construct();
		return builder.getObject();
	}

	private boolean hasCurrentGame() {
		return currentGame != null;
	}
	
	private void defineGardeners() {
		if(redAndYellowNumbersHasBeenSelected()) {
			int redNumber = currentTurn.getSelectedRedNumber();
			int yellowNumber = currentTurn.getSelectedYellowNumber();
			if(redNumber > yellowNumber) {
				defineGardeners(currentGame.getRedGardener(), currentGame.getYellowGardener());
			} else if(redNumber < yellowNumber) {
				defineGardeners(currentGame.getYellowGardener(), currentGame.getRedGardener());
			} else {
				restartTurn();
			}
		}
	}

	private boolean redAndYellowNumbersHasBeenSelected() {
		return hasCurrentGame() && currentTurn.hasSelectedRedFlower() && currentTurn.hasSelectedYellowFlower();
	}

	private void defineGardeners(Player seniorGardener, Player yellowGardener) {
		currentTurn.setSeniorGardener(seniorGardener);
		currentTurn.setJuniorGardener(yellowGardener);
		hideRedFlowerNumber();
		hideYellowFlowerNumber();
		notifyGardenersAreDefined();
		goToNextStep();
	}

	private void updateTurnStatus(TurnStatus status) {
		currentTurn.setStatus(status);
		notifyUpdatedTurnStatus();
	}
	
	protected void notifyUpdatedBoard() {
		for(GameControllerObserver observer : observers) {
			observer.updateBoard();
		}
	}

	protected void notifyUpdatedFlowers(GardenerColor color) {
		switch(color) {
			case RED:
				notifyUpdatedRedFlowers();
				break;
			case YELLOW:
				notifyUpdatedYellowFlowers();
				break;
			default:
				break;
		}
	}

	protected void notifyUpdatedRedFlowers() {
		for(GameControllerObserver observer : observers) {
			observer.updateRedFlowers();
		}
	}

	protected void notifyUpdatedYellowFlowers() {
		for(GameControllerObserver observer : observers) {
			observer.updateYellowFlowers();
		}
	}

	protected void notifyGardenersAreDefined() {
		String seniorGardener = currentTurn.getSeniorGardener().getName();
		String juniorGardener = currentTurn.getJuniorGardener().getName();
		for(GameControllerObserver observer : observers) {
			observer.updateGardeners(seniorGardener, juniorGardener);
		}
	}

	protected void notifyUpdatedTurnStatus() {
		String status = currentTurn.getStatus().getDescricao();
		for(GameControllerObserver observer : observers) {
			observer.updateStatus(status);
		}
	}

	protected void notifyUpdatedWithdrawRedFlowers() {
		for(GameControllerObserver observer : observers) {
			observer.updateWithdrawRedFlowers();
		}
	}
	
	protected void notifyUpdatedWithdrawYellowFlowers() {
		for(GameControllerObserver observer : observers) {
			observer.updateWithdrawYellowFlowers();
		}
	}

	private void notifyWithdrawedRedFlowers() {
		for(GameControllerObserver observer : observers) {
			observer.withdrawedRedFlowers();
		}
	}

	private void notifyWithdrawedYellowFlowers() {
		for(GameControllerObserver observer : observers) {
			observer.withdrawedYellowFlowers();
		}
	}

}
