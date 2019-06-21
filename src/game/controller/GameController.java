
package game.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.model.Game;
import game.model.GameStatus;
import game.model.board.Board;
import game.model.board.Square;
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
import game.model.visitor.BoardBloomPointsVisitor;
import game.model.visitor.BoardRedBloomPoinstVisitor;
import game.model.visitor.BoardYellowBloomPoinstVisitor;
import game.controller.state.AbstractControllerState;
import game.controller.state.UnitializedGame;

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
	private AbstractControllerState state;

	public GameController() {
		init();
	}
	
	private void init() {
		observers 					= new ArrayList<>();
		game 						= null;
		currentRound 				= null;
		currentBoard 				= null;
		currentTurn 				= null;
		selectedSquare 				= null;
		visibleRedFlowerNumber 		= null;
		visibleYellowFlowerNumber 	= null;
		currentFrog					= null;
		setState(new UnitializedGame(this));
	}

	@Override
	public void attach(GameControllerObserver observer) {
		observers.add(observer);
	}
	
	@Override
	public void startGame(String redGardener, String yellowGardener, int gameType) throws Exception {
		state.startGame(redGardener, yellowGardener, gameType);
	}

	@Override
	public void selectRedFlower(int index) throws Exception {
		state.selectRedFlower(index);
	}
	
	@Override
	public void selectYellowFlower(int index) throws Exception {
		state.selectYellowFlower(index);
	}

	@Override
	public void defineJuniorGardenerFlowerSquare(int row, int column) throws Exception {
		state.defineJuniorGardenerFlowerSquare(row, column);
	}

	@Override
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		state.defineSeniorGardenerFlowerSquare(row, column);
	}

	@Override
	public void defineSeniorGardenerFrogSquare(int row, int column) throws Exception {
		state.defineSeniorGardenerFrogSquare(row, column);
	}

	@Override
	public void executeHaruIchiban(int row, int column) throws Exception {
		state.executeHaruIchiban(row, column);
	}
	
	@Override
	public void defineDarkenedNenufar(int row, int column) throws Exception {
		state.defineDarkenedNenufar(row, column);
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
		return state.hasBoardInfoAt(rowIndex, columnIndex);
	}

	@Override
	public String getBoardInfoAt(int rowIndex, int columnIndex) {
		return state.getBoardInfoAt(rowIndex, columnIndex);
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
		state.withdrawRedFlower(index);
	}

	@Override
	public void withdrawYellowFlowerAt(int index) throws Exception {
		state.withdrawYellowFlower(index);
	}

	@Override
	public GameStatus getTurnStatus() {
		return state.getStatus();
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

	private boolean canWithdrawRedFlowerAt(int index, boolean checkStatus) {
		return canWithdrawFlowerAt(index, currentTurn.getRedFlowers(), checkStatus);
	}
	
	private boolean canWithdrawYellowFlowerAt(int index, boolean checkStatus) {
		return canWithdrawFlowerAt(index, currentTurn.getYellowFlowers(), checkStatus);
	}

	private boolean canWithdrawFlowerAt(int index, List<Flower> scope, boolean checkStatus) {
		return (!checkStatus || isWithdrawFlowerStatus()) && scope.size() <= index && game.getRoundQuantity() - currentRound.getTurns().size() > index;
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
			updateTurnStatus(GameStatus.WITHDRAW_FLOWER);
		} else {
			updateTurnStatus(GameStatus.FLOWER_SELECTION);
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

	private void finishRoundOrUpdateTurnStatus(GameStatus status) {
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
		return isCurrentTurnStatus(GameStatus.WITHDRAW_FLOWER);
	}

	private boolean isFlowerSelectionStatus() {
		return isCurrentTurnStatus(GameStatus.FLOWER_SELECTION);
	}
	
	private boolean isCurrentTurnStatus(GameStatus status) {
		return hasCurrentGame() && currentTurn.isStatus(status);
	}
	
	private boolean hasCurrentGame() {
		return game != null;
	}

	private void updateTurnStatus(GameStatus status) {
		if(currentTurn != null) {
			currentTurn.setStatus(status);
			notifyUpdatedTurnStatus();
		}
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
		String status = state.toString();
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

	public void setGame(Game<Nenufar> game) {
		this.game = game;
		this.currentBoard = game.getBoard();
		this.goToNextRound();
	}

	public void setState(AbstractControllerState state) {
		this.state = state;
		updateTurnStatus(state.getStatus());
		System.out.println("Novo estado: "+state);
	}

	public Flower removeRoundWithdrawRedFlower(int index) {
		Flower flower = currentRound.removeWithdrawRedFlower(index);
		notifyUpdatedWithdrawRedFlowers();
		return flower;
	}

	public void addTurnRedFlower(Flower flower) {
		currentTurn.addRedFlower(flower);
		notifyUpdatedRedFlowers();
	}

	public Flower removeRoundWithdrawYellowFlower(int index) {
		Flower flower = currentRound.removeWithdrawYellowFlower(index);
		notifyUpdatedWithdrawYellowFlowers();
		return flower;
	}

	public void addTurnYellowFlower(Flower flower) {
		currentTurn.addYellowFlower(flower);
		notifyUpdatedYellowFlowers();
	}
	
	public boolean checkWithdrawedRedFlowers() {
		boolean withdrawed = withdrawedRedFlowers();
		if(withdrawed) {
			notifyWithdrawedRedFlowers();
		}
		return withdrawed;
	}
	
	public boolean checkWithdrawedYellowFlowers() {
		boolean withdrawed = withdrawedYellowFlowers();
		if(withdrawed) {
			notifyWithdrawedYellowFlowers();
		}
		return withdrawed;
	}

	private boolean withdrawedRedFlowers() {
		return currentTurn.getRedFlowers().size() == AVAILABLE_SELECT_FLOWERS;
	}

	private boolean withdrawedYellowFlowers() {
		return currentTurn.getYellowFlowers().size() == AVAILABLE_SELECT_FLOWERS;
	}

	public boolean hasTurnSelectedRedFlower() {
		return currentTurn.hasSelectedRedFlower();
	}

	public boolean hasTurnSelectedYellowFlower() {
		return currentTurn.hasSelectedYellowFlower();
	}

	public void setTurnSelectedRedFlower(int index) {
		currentTurn.setSelectedRedFlower(index);
		notifyUpdatedRedFlowers();
	}

	public void setTurnSelectedYellowFlower(int index) {
		currentTurn.setSelectedYellowFlower(index);
		notifyUpdatedYellowFlowers();
	}

	public int getTurnSelectedRedNumber() {
		return currentTurn.getSelectedRedNumber();
	}

	public int getTurnSelectedYellowNumber() {
		return currentTurn.getSelectedYellowNumber();
	}

	public Gardener getRedGardener() {
		return game.getRedGardener();
	}

	public Gardener getYellowGardener() {
		return game.getYellowGardener();
	}

	public void updateTurnGardeners(Gardener seniorGardener, Gardener yellowGardener) {
		currentTurn.setSeniorGardener(seniorGardener);
		currentTurn.setJuniorGardener(yellowGardener);
		hideRedFlowerNumber();
		hideYellowFlowerNumber();
		notifyGardenersAreDefined();
	}

	public Board<Nenufar> getCurrentBoard() {
		return currentBoard;
	}

	public Turn getCurrentTurn() {
		return currentTurn;
	}
	
	public void moveGardenerFlowerToSquare(Square<Nenufar> square, Flower flower, GardenerColor color) {
		currentTurn.removeFlower(flower, color);
		currentBoard.getElementAtSquare(square).setElement(flower);
		currentBoard.getElementAtSquare(square).activeTopSide();
		notifyUpdatedFlowers(color);
		notifyUpdatedBoard();
	}

	public void goToNextRound() {
		currentRound = createRound();
		game.addRound(currentRound);
		clearBoard();
		notifyStartedRound();
		currentTurn = null;
		goToNextTurn();
	}

	public void setCurrentFrog(Frog frog) {
		this.currentFrog = frog;
	}
	
	public void setSelectedSquare(Square<Nenufar> square) {
		selectedSquare = square;
		notifyUpdatedBoard();
	}

	public boolean hasCurrentFrog() {
		return currentFrog != null;
	}

	public Frog getCurrentFrog() {
		return currentFrog;
	}

	public boolean hasSelectedSquare() {
		return selectedSquare != null;
	}

	public void setElementAtSquare(Nenufar nenufar, int row, int column) {
		currentBoard.setElementAtSquare(nenufar, row, column);
		notifyUpdatedBoard();
	}

	public Square<Nenufar> getSelectedSquare() {
		return selectedSquare;
	}

}
