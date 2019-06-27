
package game.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.model.board.Board;
import game.model.board.Square;
import game.model.factory.AbstractGardenerFactory;
import game.model.factory.RedGardenerFactory;
import game.model.factory.YellowGardenerFactory;
import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.game.Game;
import game.model.game.GameStatus;
import game.model.game.Round;
import game.model.game.Turn;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerColor;
import game.model.gardener.GardenerItem;
import game.model.gardener.decorator.GardenerPulverizadorDecorator;
import game.model.gardener.decorator.GardenerTesouraPodaDecorator;
import game.model.nenufar.Nenufar;
import game.model.strategy.square.CompareNonFloweredNenufarStrategy;
import game.model.strategy.square.CompareRedFrogNenufarStrategy;
import game.model.strategy.square.CompareYellowFrogNenufarStrategy;
import game.model.visitor.CountNenufarVisitor;
import game.model.visitor.RemoveNenufarElementVisitor;
import game.model.visitor.ResetBoardElementVisitor;
import game.controller.state.AbstractControllerState;
import game.controller.state.GameControllerStateAccess;
import game.controller.state.UnitializedGame;

public class GameController implements GameControllerInterface, GameControllerStateAccess {
	
	public static final int AVAILABLE_SELECT_FLOWERS = 3;
	private List<GameControllerObserver> observers;
	private Game<Nenufar> game;
	private Round currentStep;
	private Board<Nenufar> currentBoard;
	private Turn currentTurn;
	private Square<Nenufar> selectedSquare;
	private Flower visibleRedFlowerNumber;
	private Flower visibleYellowFlowerNumber;
	private Frog currentFrog;
	private AbstractControllerState state;
	private Gardener croakGardener;
	private List<GardenerItem> redGardenerItems;
	private List<GardenerItem> yellowGardenerItems;

	public GameController() {
		init();
	}
	
	private void init() {
		observers 					= new ArrayList<>();
		game 						= null;
		currentStep 				= null;
		currentBoard 				= null;
		currentTurn 				= null;
		selectedSquare 				= null;
		visibleRedFlowerNumber 		= null;
		visibleYellowFlowerNumber 	= null;
		currentFrog					= null;
		croakGardener				= null;
		redGardenerItems			= new ArrayList<>();
		yellowGardenerItems			= new ArrayList<>();
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
	public void redGardenerCroak() throws Exception {
		state.redGardenerCroak();
	}
	
	@Override
	public void yellowGardenerCroak() throws Exception {
		state.yellowGardenerCroak();
	}

	@Override
	public void defineSeniorGardenerFlowerSquare(int row, int column) throws Exception {
		state.defineSeniorGardenerFlowerSquare(row, column);
	}

	@Override
	public void defineFrogSquare(int row, int column) throws Exception {
		state.defineGardenerFrogSquare(row, column);
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
		return state.getStatusDescription();
	}

	@Override
	public int getBoardRowCount() {
		return state.getBoardRowCount();
	}

	@Override
	public int getBoardColumnCount() {
		return state.getboardColumnCount();
	}

	@Override
	public boolean hasBoardElementAt(int rowIndex, int columnIndex) {
		return state.hasBoardElementAt(rowIndex, columnIndex);
	}

	@Override
	public String getBoardElementAt(int rowIndex, int columnIndex) {
		return state.getBoardElementAt(rowIndex, columnIndex);
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
	public void equiparRedGardener(int item) throws Exception {
		state.equiparRedGardener(item);
	}

	@Override
	public void equiparYellowGardener(int item) throws Exception {
		state.equiparYellowGardener(item);
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
		return state.canWithdrawRedFlowerAt(index);
	}

	@Override
	public boolean canWithdrawYellowFlowerAt(int index) {
		return state.canWithdrawYellowFlowerAt(index);
	}

	@Override
	public boolean canSelectRedFlower() {
		return state.canSelectRedFlower();
	}

	@Override
	public boolean canSelectYellowFlower() {
		return state.canSelectYellowFlower();
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
		return currentStep.getWithdrawYellowFlower(index).getDescription();
	}

	@Override
	public String getWithdrawRedFlowerAt(int index) {
		return currentStep.getWithdrawRedFlower(index).getDescription();
	}

	@Override
	public int getWithdrawYellowFlowerNumberAt(int index) {
		return currentStep.getWithdrawYellowFlower(index).getNumber();
	}

	@Override
	public int getWithdrawRedFlowerNumberAt(int index) {
		return currentStep.getWithdrawRedFlower(index).getNumber();
	}

	@Override
	public int getWithdrawYellowFlowerQuantity() {
		return (hasCurrentGame()) ? currentStep.getWithdrawYellowFlowers().size() : 0;
	}

	@Override
	public int getWithdrawRedFlowerQuantity() {
		return (hasCurrentGame()) ? currentStep.getWithdrawRedFlowers().size() : 0;
	}

	@Override
	public boolean hasWithdrawYellowFlowerAt(int index) {
		return hasCurrentGame() && currentStep.getWithdrawYellowFlowers().size() > index;
	}

	@Override
	public boolean hasWithdrawRedFlowerAt(int index) {
		return hasCurrentGame() && currentStep.getWithdrawRedFlowers().size() > index;
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
		return game.getTurnLimit();
	}

	@Override
	public void setGame(Game<Nenufar> game) {
		this.game = game;
		this.currentBoard = game.getBoard();
		this.goToNextRound();
	}

	@Override
	public void setState(AbstractControllerState state) {
		this.state = state;
		if(state.isFinished()) {
			notifyFinishedGame(state.getWinnerGardener());
		} else {
			updateTurnStatus(state.getStatus());
		}
	}

	@Override
	public List<Integer> getYellowItemsToSelection() {
		return state.getYellowItemsToSelection();
	}

	@Override
	public String getItemDescription(Integer item) {
		return state.getItemDescription(item);
	}

	@Override
	public List<Integer> getRedItemsToSelection() {
		return state.getRedItemsToSelection();
	}

	@Override
	public Flower removeRoundWithdrawRedFlower(int index) {
		Flower flower = currentStep.removeWithdrawRedFlower(index);
		notifyUpdatedWithdrawRedFlowers();
		return flower;
	}

	@Override
	public void addTurnRedFlower(Flower flower) {
		currentTurn.addRedFlower(flower);
		notifyUpdatedRedFlowers();
	}

	@Override
	public Flower removeRoundWithdrawYellowFlower(int index) {
		Flower flower = currentStep.removeWithdrawYellowFlower(index);
		notifyUpdatedWithdrawYellowFlowers();
		return flower;
	}

	@Override
	public void addTurnYellowFlower(Flower flower) {
		currentTurn.addYellowFlower(flower);
		notifyUpdatedYellowFlowers();
	}

	@Override
	public boolean checkWithdrawedRedFlowers() {
		boolean withdrawed = withdrawedRedFlowers();
		if(withdrawed) {
			notifyWithdrawedRedFlowers();
		}
		return withdrawed;
	}

	@Override
	public boolean checkWithdrawedYellowFlowers() {
		boolean withdrawed = withdrawedYellowFlowers();
		if(withdrawed) {
			notifyWithdrawedYellowFlowers();
		}
		return withdrawed;
	}

	@Override
	public boolean hasTurnSelectedRedFlower() {
		return currentTurn.hasSelectedRedFlower();
	}

	@Override
	public boolean hasTurnSelectedYellowFlower() {
		return currentTurn.hasSelectedYellowFlower();
	}

	@Override
	public void setTurnSelectedRedFlower(int index) {
		currentTurn.setSelectedRedFlower(index);
		notifyUpdatedRedFlowers();
	}

	@Override
	public void setTurnSelectedYellowFlower(int index) {
		currentTurn.setSelectedYellowFlower(index);
		notifyUpdatedYellowFlowers();
	}

	@Override
	public int getTurnSelectedRedNumber() {
		return currentTurn.getSelectedRedNumber();
	}

	@Override
	public int getTurnSelectedYellowNumber() {
		return currentTurn.getSelectedYellowNumber();
	}

	@Override
	public Gardener getRedGardener() {
		return addGardenerDecoration(game.getRedGardener(), redGardenerItems);
	}

	@Override
	public Gardener getYellowGardener() {
		return addGardenerDecoration(game.getYellowGardener(), yellowGardenerItems);
	}

	@Override
	public void updateTurnGardeners(Gardener seniorGardener, Gardener yellowGardener) {
		currentTurn.setSeniorGardener(seniorGardener);
		currentTurn.setJuniorGardener(yellowGardener);
		hideRedFlowerNumber();
		hideYellowFlowerNumber();
		notifyGardenersAreDefined();
	}

	@Override
	public Board<Nenufar> getCurrentBoard() {
		return currentBoard;
	}

	@Override
	public Turn getCurrentTurn() {
		return currentTurn;
	}

	@Override
	public void moveGardenerFlowerToSquare(Square<Nenufar> square, Flower flower, GardenerColor color) {
		currentTurn.removeFlower(flower, color);
		getGardenerByColor(color).florescer(square, flower);
		checkRemoveFrogs();
		notifyUpdatedFlowers(color);
		notifyUpdatedBoard();
	}

	@Override
	public void goToNextRound() {
		currentStep = createRound();
		game.addStep(currentStep);
		clearBoard();
		notifyStartedRound();
		currentTurn = null;
		goToNextTurn();
	}

	@Override
	public void setCurrentFrog(Frog frog) {
		this.currentFrog = frog;
	}

	@Override
	public void setSelectedSquare(Square<Nenufar> square) {
		selectedSquare = square;
		notifyUpdatedBoard();
	}

	@Override
	public boolean hasCurrentFrog() {
		return currentFrog != null;
	}

	@Override
	public Frog getCurrentFrog() {
		return currentFrog;
	}

	@Override
	public boolean hasSelectedSquare() {
		return selectedSquare != null;
	}

	@Override
	public void setElementAtSquare(Nenufar nenufar, int row, int column) {
		currentBoard.setElementAtSquare(nenufar, row, column);
		checkRemoveFrogs();
		notifyUpdatedBoard();
	}

	@Override
	public Square<Nenufar> getSelectedSquare() {
		return selectedSquare;
	}

	@Override
	public void requestCroak() {
		for(GameControllerObserver observer : observers) {
			observer.showCroakButton();
		}
	}

	@Override
	public void cancelCroak() {
		for(GameControllerObserver observer : observers) {
			observer.hideCroakButton();
		}
	}

	@Override
	public int getRedPoints() {
		return game.getRedPoints();
	}

	@Override
	public int getYellowPoints() {
		return game.getYellowPoints();
	}

	@Override
	public Round getCurrentRound() {
		return currentStep;
	}

	@Override
	public void setVisibleRedFlowerNumber(Flower redFlower) {
		visibleRedFlowerNumber = redFlower;
		notifyUpdatedRedFlowers();
	}

	@Override
	public void setVisibleYellowFlowerNumber(Flower yellowFlower) {
		visibleYellowFlowerNumber = yellowFlower;
		notifyUpdatedYellowFlowers();
	}

	@Override
	public void setCroakGardener(Gardener gardener) {
		this.croakGardener = gardener;
	}

	@Override
	public void requestMoveRedFrog() {
		for(GameControllerObserver observer : observers) {
			observer.requestMoveRedFrog();
		}
	}

	@Override
	public void requestMoveYellowFrog() {
		for(GameControllerObserver observer : observers) {
			observer.requestMoveYellowFrog();
		}
	}

	@Override
	public Gardener getCroakGardener() {
		return croakGardener;
	}
	
	@Override
	public void addYellowGardenerItem(GardenerItem gardenerItem) {
		GardenerItem item = game.removeYellowGardenerItem(gardenerItem);
		if(item != null) {
			yellowGardenerItems.add(item);
			notifyUsedYellowGardenerItem(item.getDescription());
		}
	}

	@Override
	public void addRedGardenerItem(GardenerItem gardenerItem) {
		GardenerItem item = game.removeRedGardenerItem(gardenerItem);
		if(item != null) {
			redGardenerItems.add(item);
			notifyUsedRedGardenerItem(item.getDescription());
		}
	}

	@Override
	public void removeRedGardenerItems() {
		redGardenerItems = new ArrayList<>();
	}

	@Override
	public void removeYellowGardenerItems() {
		yellowGardenerItems = new ArrayList<>();
	}
	
	@Override
	public void addScore(int redPlayer, int yellowPlayer) {
		currentStep.addRedPoints(redPlayer);
		currentStep.addYellowPoints(yellowPlayer);
		notifyUpdatedScore(redPlayer, yellowPlayer);
	}

	@Override
	public List<GardenerItem> getGameYellowGardenerItems() {
		return game.getYellowGardenerItens();
	}

	@Override
	public List<GardenerItem> getGameRedGardenerItems() {
		return game.getRedGardenerItens();
	}

	@Override
	public void blockItems(boolean b) {
		for(GameControllerObserver observer : observers) {
			observer.blockItems(b);
		}
	}

	private Gardener getGardenerByColor(GardenerColor color) {
		return (color == GardenerColor.RED) ? getRedGardener() : getYellowGardener();
	}

	private boolean withdrawedRedFlowers() {
		return currentTurn.getRedFlowers().size() == AVAILABLE_SELECT_FLOWERS;
	}

	private boolean withdrawedYellowFlowers() {
		return currentTurn.getYellowFlowers().size() == AVAILABLE_SELECT_FLOWERS;
	}

	private void clearBoard() {
		currentBoard.accept(new ResetBoardElementVisitor());
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
		int rounds = game.getTurnLimit();
		for(int i = 1; i <= rounds; i++) {
			flowers.add(factory.createFlower(i));
		}
		Collections.shuffle(flowers);
		return flowers;
	}

	public void goToNextTurn() {
		if(currentStep.getTurns().size() >= game.getTurnLimit()) {
			goToNextRound();
			return;
		}
		currentFrog = null;
		Turn olderTurn = currentTurn;
		currentTurn = new Turn();
		if(olderTurn != null) {
			currentTurn.setRedFlowers(olderTurn.getRedFlowers());
			currentTurn.setYelllowFlowers(olderTurn.getYellowFlowers());
		}
		currentStep.addTurn(currentTurn);
		checkRemoveFrogs();
		notifyUpdatedRedFlowers();
		notifyUpdatedYellowFlowers();
		notifyUpdatedBoard();
	}

	private void checkRemoveFrogs() {
		if(countNonFloweredNenufars() <= 2) {
			removeFrogs();
		}
	}

	private int countNonFloweredNenufars() {
		CountNenufarVisitor visitor = new CountNenufarVisitor(new CompareNonFloweredNenufarStrategy());
		currentBoard.accept(visitor);
		return visitor.getCount();
	}

	private void removeFrogs() {
		currentBoard.accept(new RemoveNenufarElementVisitor(new CompareRedFrogNenufarStrategy()));
		currentBoard.accept(new RemoveNenufarElementVisitor(new CompareYellowFrogNenufarStrategy()));
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

	protected void notifyFinishedGame(String winner) {
		for(GameControllerObserver observer : observers) {
			observer.updateWinner(winner);
		}
	}
	
	protected void notifyUpdatedBoard() {
		for(GameControllerObserver observer : observers) {
			observer.updateBoard();
		}
	}

	protected void notifyUsedYellowGardenerItem(String item) {
		for(GameControllerObserver observer : observers) {
			observer.yellowPlayerUsedItem(item);
			observer.updateYellowItems();
		}
	}

	protected void notifyUsedRedGardenerItem(String item) {
		for(GameControllerObserver observer : observers) {
			observer.redPlayerUsedItem(item);
			observer.updateRedItems();
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

	private Gardener addGardenerDecoration(Gardener gardener, List<GardenerItem> items) {
		for(GardenerItem item : items) {
			switch(item) {
			case PULVERIZADOR:
				gardener = new GardenerPulverizadorDecorator(gardener);
				break;
			case TESOURA_PODA:
				gardener = new GardenerTesouraPodaDecorator(gardener);
				break;
			default:
				break;
			}
		}
		return gardener;
	}

	protected void notifyUpdatedScore(int addedScore1, int addedScore2) {
		int score1 = game.getRedPoints();
		int score2 = game.getYellowPoints();
		for(GameControllerObserver observer : observers) {
			observer.updateScore(addedScore1, score1, addedScore2, score2);
		}
	}

	private void notifyStartedRound() {
		int currentRound = game.getSteps().size();
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

}
