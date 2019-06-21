
package game.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.model.Direction;
import game.model.Game;
import game.model.TurnStatus;
import game.model.board.Board;
import game.model.board.Square;
import game.model.builder.game.GameBuilder;
import game.model.builder.game.GameBuilderHashMap;
import game.model.builder.game.GameDirector;
import game.model.Round;
import game.model.Turn;
import game.model.factory.AbstractGardenerFactory;
import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.frog.RedFrog;
import game.model.frog.YellowFrog;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;
import game.model.nenufar.NenufarSide;
import game.model.visitor.BoardBloomPointsVisitor;
import game.model.visitor.BoardRedBloomPoinstVisitor;
import game.model.visitor.BoardYellowBloomPoinstVisitor;
import game.model.nenufar.LightedNenufar;
import game.controller.exception.InvallidDarkenedNenufarSquareException;
import game.controller.exception.InvallidDefineDarkenedNenufarTimeException;
import game.controller.exception.InvallidHaruIchibanTimeException;
import game.controller.exception.InvallidJuniorGardenerFlowerSquareException;
import game.controller.exception.InvallidJuniorGardenerFlowerTimeException;
import game.controller.exception.InvallidRedFlowerTimeException;
import game.controller.exception.InvallidRedWithdrawTimeException;
import game.controller.exception.InvallidSeniorGardenerFlowerSquareException;
import game.controller.exception.InvallidSeniorGardenerFlowerTimeException;
import game.controller.exception.InvallidSeniorGardenerFrogSquareException;
import game.controller.exception.InvallidSeniorGardenerFrogTimeException;
import game.controller.exception.InvallidYellowFlowerTimeException;
import game.controller.exception.InvallidYellowWithdrawTimeException;

public class GameController implements GameControllerInterface {
	
	private static final int AVAILABLE_SELECT_FLOWERS = 3;
	private List<GameControllerObserver> observers;
	private Game<Nenufar> game;
	private Round currentRound;
	private Board<Nenufar> currentBoard;
	private Turn currentTurn;
	private Square<Nenufar> selectedSquare;
	private Flower visibleRedFlowerNumber;
	private Flower visibleYellowFlowerNumber;
	private Frog currentFrog;

	public GameController() {
		observers = new ArrayList<>();
		init();
	}
	
	private void init() {
		game 						= null;
		currentRound 				= null;
		currentBoard 				= null;
		currentTurn 				= null;
		selectedSquare 				= null;
		visibleRedFlowerNumber 		= null;
		visibleYellowFlowerNumber 	= null;
		currentFrog					= null;
	}

	@Override
	public void attach(GameControllerObserver observer) {
		observers.add(observer);
	}
	
	@Override
	public void startGame(String redGardener, String yellowGardener, int gameType) {
		game  = createGame(redGardener, yellowGardener, gameType);
		currentBoard = game.getBoard();
		goToNextRound();
	}

	@Override
	public void selectRedFlower(int index) throws Exception {
		if(canSelectRedFlower()) {
			Flower selectedFlower = currentTurn.getRedFlower(index);
			currentTurn.setSelectedRedFlower(selectedFlower);
			notifyUpdatedRedFlowers();
			defineGardeners();
		} else {
			throw new InvallidRedFlowerTimeException();
		}
	}
	
	@Override
	public void selectYellowFlower(int index) throws Exception {
		if(canSelectYellowFlower()) {
			Flower selectedFlower = currentTurn.getYellowFlower(index);
			currentTurn.setSelectedYellowFlower(selectedFlower);
			notifyUpdatedYellowFlowers();
			defineGardeners();
		} else {
			throw new InvallidYellowFlowerTimeException();
		}
	}

	@Override
	public void defineJuniorGardenerFlowerSquare(int row, int column) throws Exception {
		if(canSelectJuniorGardenerFlowerSquare()) {
			if(canMoveJuniorGardenerFlowerToSquare(row, column)) {
				moveJuniorGardenerFlowerToSquare(row, column);
				goToNextStep();
			} else {
				throw new InvallidJuniorGardenerFlowerSquareException();
			}
		} else {
			throw new InvallidJuniorGardenerFlowerTimeException();
		}
	}

	@Override
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		if(canSelectSeniorGardenerFlowerSquare()) {
			if(canMoveSeniorGardenerFlowerToSquare(row, column)) {
				moveSeniorGardenerFlowerToSquare(row, column);
			} else {
				throw new InvallidSeniorGardenerFlowerSquareException();
			}
		} else {
			throw new InvallidSeniorGardenerFlowerTimeException();
		}
	}

	@Override
	public void defineSeniorGardenerFrogSquare(int row, int column) throws Exception {
		if(canDefineSeniorGardenerFrogSquare()) {
			if(canMoveSeniorGardenerFrogToSquare(row, column)) {
				moveSeniorGardenerFrogToSquare(row, column);
				goToNextStep();
			} else {
				throw new InvallidSeniorGardenerFrogSquareException();
			}
		} else {
			throw new InvallidSeniorGardenerFrogTimeException();
		}
	}

	@Override
	public void executeHaruIchiban(int row, int column) throws Exception {
		if(canExecuteHaruIchiban()) {
			if(hasSelectedSquareToHaruIchiban()) {
				callHaruIchiban(row, column);
			} else {
				selectSquareToHaruIchiban(row, column);
			}
		} else {
			throw new InvallidHaruIchibanTimeException();
		}
	}
	
	@Override
	public void defineDarkenedNenufar(int row, int column) throws Exception {
		if(canDefineDarkenedNenufar()) {
			if(canDefineDarkenedNenufarToSquare(row, column)) {
				defineDarkenedNenufarToSquare(row, column);
				goToNextStep();
			} else {
				throw new InvallidDarkenedNenufarSquareException();
			}
		} else {
			throw new InvallidDefineDarkenedNenufarTimeException();
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
		return AVAILABLE_SELECT_FLOWERS;
	}

	@Override
	public int getAvailableYellowFlowerQuantity() {
		return AVAILABLE_SELECT_FLOWERS;
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
	public void withdrawRedFlower(int index) throws Exception {
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
			throw new InvallidRedWithdrawTimeException();
		}
	}

	@Override
	public void withdrawYellowFlowerAt(int index) throws Exception {
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
			throw new InvallidYellowWithdrawTimeException();
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

	@Override
	public int getRoundQuantity() {
		return game.getRoundQuantity();
	}

	private Game<Nenufar> createGame(String redPlayer, String yellowPlayer, int gameType) {
		GameBuilderHashMap 	  games    = GameBuilderHashMap.getInstance();
		GameBuilder<Nenufar>  builder  = games.get(gameType);
		GameDirector<Nenufar> director = new GameDirector<>(builder);
		director.construct(redPlayer, yellowPlayer);
		return builder.getGame();
	}
	
	private boolean canWithdrawRedFlowerAt(int index, boolean checkStatus) {
		return canWithdrawFlowerAt(index, currentTurn.getRedFlowers(), checkStatus);
	}
	
	private boolean canWithdrawYellowFlowerAt(int index, boolean checkStatus) {
		return canWithdrawFlowerAt(index, currentTurn.getYellowFlowers(), checkStatus);
	}

	private boolean canWithdrawFlowerAt(int index, List<Flower> scope, boolean checkStatus) {
		return (!checkStatus || isWithdrawFlowerStatus()) && scope.size() <= index && game.getRoundQuantity() - currentRound.getTurns().size() > index;
	}

	private void goToNextRound() {
		currentRound = createRound();
		game.addRound(currentRound);
		clearBoard();
		notifyStartedRound();
		currentTurn = null;
		goToNextTurn();
	}

	private void clearBoard() {
		int rows = currentBoard.getRows();
		int cols = currentBoard.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Nenufar element = currentBoard.getElementAtSquare(i, j);
				if(element != null) {
					element.resetElement();
				}
			}
		}
		notifyUpdatedBoard();
	}

	private Round createRound() {
		return new Round(createRoundRedFlowers(), createRoundYellowFlowers());
	}

	private List<Flower> createRoundRedFlowers() {
		return createRoundFlowers(RedGardenerFactory.getInstance());
	}

	private List<Flower> createRoundYellowFlowers() {
		return createRoundFlowers(YellowGardenerFactory.getInstance());
	}

	private List<Flower> createRoundFlowers(AbstractGardenerFactory factory) {
		List<Flower> flowers = new ArrayList<>();
		int rounds = game.getRoundQuantity();
		for(int i = 1; i <= rounds; i++) {
			flowers.add(factory.createFlower(i));
		}
		Collections.shuffle(flowers);
		return flowers;
	}

	private void goToNextTurn() {
		currentFrog = null;
		Turn olderTurn = currentTurn;
		currentTurn = new Turn();
		if(olderTurn != null) {
			currentTurn.setRedFlowers(olderTurn.getRedFlowers());
			currentTurn.setYelllowFlowers(olderTurn.getYellowFlowers());
		}
		currentRound.addTurn(currentTurn);
		if(getRoundQuantity() - currentRound.getTurns().size() < 2) {
			removeFrogs();
		}
		notifyUpdatedRedFlowers();
		notifyUpdatedYellowFlowers();
		defineInitialTurnStatus();
	}

	private void removeFrogs() {
		Nenufar nenufar;
		int rows = currentBoard.getRows();
		int cols = currentBoard.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				nenufar = currentBoard.getElementAtSquare(i, j);
				if(nenufar != null && nenufar.getElement() != null && (nenufar.getElement().getClass() == RedFrog.class || nenufar.getElement().getClass() == YellowFrog.class)) {
					nenufar.setElement(null);
				}
			}
		}
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
				finishRoundOrUpdateTurnStatus(TurnStatus.SENIOR_GARDENER_FLOWER_SQUARE);
				break;
			case SENIOR_GARDENER_FLOWER_SQUARE:
				finishRoundOrUpdateTurnStatus(TurnStatus.JUNIOR_GARDENER_HARU_ICHIBAN);
				break;
			case SENIOR_GARDENER_FROG_SQUARE:
				updateTurnStatus(TurnStatus.JUNIOR_GARDENER_HARU_ICHIBAN);
				break;
			case JUNIOR_GARDENER_HARU_ICHIBAN:
				finishRoundOrUpdateTurnStatus(TurnStatus.SENIOR_GARDENER_DARKENED_NENUFAR);
				break;
			case SENIOR_GARDENER_DARKENED_NENUFAR:
				goToNextTurn();
				break;
			default:
				break;
		}
	}

	private void finishRoundOrUpdateTurnStatus(TurnStatus status) {
		int redPoints = calculateRedPoints();
		int yellowPoints = calculateYellowPoints();
		if(redPoints > 0 || yellowPoints > 0) {
			game.addRedPoints(redPoints);
			game.addYellowPoints(yellowPoints);
			notifyUpdatedScore();
			goToNextRound();
		} else {
			updateTurnStatus(status);
		}
	}

	private int calculateRedPoints() {
		BoardBloomPointsVisitor visitor = new BoardRedBloomPoinstVisitor();
		visitor.visit(currentBoard);
		return visitor.getPoints();
	}

	private int calculateYellowPoints() {
		BoardBloomPointsVisitor visitor = new BoardYellowBloomPoinstVisitor();
		visitor.visit(currentBoard);
		return visitor.getPoints();
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

	private boolean canDefineSeniorGardenerFrogSquare() {
		return isCurrentTurnStatus(TurnStatus.SENIOR_GARDENER_FROG_SQUARE) && currentFrog != null;
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
		return currentTurn.getRedFlowers().size() == AVAILABLE_SELECT_FLOWERS;
	}

	private boolean withdrawedYellowFlowers() {
		return currentTurn.getYellowFlowers().size() == AVAILABLE_SELECT_FLOWERS;
	}

	private boolean canMoveJuniorGardenerFlowerToSquare(int row, int column) {
		Nenufar nenufar = currentBoard.getElementAtSquare(row, column);
		return (nenufar != null && nenufar.getActiveSide() == NenufarSide.DARKENED && !nenufar.isFlowered());
	}

	private boolean canMoveSeniorGardenerFrogToSquare(int row, int column) {
		Nenufar nenufar = currentBoard.getElementAtSquare(row, column);
		return (currentFrog != null && nenufar != null && nenufar.getElement() == null && nenufar.getActiveSide() == NenufarSide.LIGHTED);
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
		Nenufar lastElement		= square.getElement();
		if(lastElement != null && lastElement.getElement() != null && (lastElement.getElement().getClass() == RedFrog.class || lastElement.getElement().getClass() == YellowFrog.class)) {
			currentFrog = (Frog) lastElement.getElement();
		} else {
			currentFrog = null;
		}
		Flower flower 			= currentTurn.getSelectedSeniorGardenerFlower();
		GardenerColor color 	= currentTurn.getSeniorGardenerColor();
		moveGardenerFlowerToSquare(square, flower, color);
		if(currentFrog != null) {
			updateTurnStatus(TurnStatus.SENIOR_GARDENER_FROG_SQUARE);
		} else {
			goToNextStep();
		}
	}
	
	private void moveSeniorGardenerFrogToSquare(int row, int column) {
		currentBoard.getElementAtSquare(row, column).setElement(currentFrog);
		notifyUpdatedBoard();
	}
	
	private void moveGardenerFlowerToSquare(Square<Nenufar> square, Flower flower, GardenerColor color) {
		currentTurn.removeFlower(flower, color);
		currentBoard.getElementAtSquare(square).setElement(flower);
		currentBoard.getElementAtSquare(square).activeTopSide();
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
		return nenufar != null && nenufar.getClass() == LightedNenufar.class && nenufar.getElement() == null;
	}
	
	private void defineDarkenedNenufarToSquare(int row, int column) {
		currentBoard.getElementAtSquare(row, column).activeBottomSide();
		notifyUpdatedBoard();
	}
	
	private boolean hasCurrentGame() {
		return game != null;
	}
	
	private void defineGardeners() {
		if(redAndYellowNumbersHasBeenSelected()) {
			int redNumber = currentTurn.getSelectedRedNumber();
			int yellowNumber = currentTurn.getSelectedYellowNumber();
			if(redNumber > yellowNumber) {
				defineGardeners(game.getRedGardener(), game.getYellowGardener());
			} else if(redNumber < yellowNumber) {
				defineGardeners(game.getYellowGardener(), game.getRedGardener());
			} else {
				restartTurn();
			}
		}
	}

	private boolean redAndYellowNumbersHasBeenSelected() {
		return hasCurrentGame() && currentTurn.hasSelectedRedFlower() && currentTurn.hasSelectedYellowFlower();
	}

	private void defineGardeners(Gardener seniorGardener, Gardener yellowGardener) {
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

	private void notifyUpdatedScore() {
		int score1 = game.getRedPoints();
		int score2 = game.getYellowPoints();
		for(GameControllerObserver observer : observers) {
			observer.updateScore(score1, score2);
		}
	}

	private void notifyStartedRound() {
		int currentRound = game.getRounds().size();
		for(GameControllerObserver observer : observers) {
			observer.startedRound(currentRound);
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
