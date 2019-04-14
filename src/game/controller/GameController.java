package game.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.controller.factory.NenufarBoardFactory;
import game.controller.factory.NenufarFactory;
import game.model.NenufarBoard;
import game.model.NenufarBoardType;
import game.model.NenufarType;
import game.model.ComponentInterface;
import game.model.Flower;
import game.model.Nenufar;
import game.model.RedFlower;
import game.model.YellowFlower;

public class GameController implements GameControllerInterface {

	private static GameController instance;
	private List<GameControllerObserver> observers;
	private NenufarBoard board;
	private List<Flower> redFlowers;
	private List<Flower> yellowFlowers;
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	private GameController() {
		this.init();
	}
	
	private void init() {
		this.observers 	 	= new ArrayList<>();
		this.board 		 	= NenufarBoardFactory.getInstance().create(NenufarBoardType.EMPTY);
		this.redFlowers 	= new ArrayList<>();
		this.yellowFlowers  = new ArrayList<>();
	}

	@Override
	public void startGame() {
		this.board 			= NenufarBoardFactory.getInstance().create(NenufarBoardType.START);
		this.redFlowers 	= new ArrayList<>();
		this.yellowFlowers  = new ArrayList<>();
		for(int i = 1; i <= 8; i++) {
			this.redFlowers.add(new RedFlower(i));
		}
		for(int i = 1; i <= 8; i++) {
			this.yellowFlowers.add(new YellowFlower(i));
		}
		Collections.shuffle(this.redFlowers);
		Collections.shuffle(this.yellowFlowers);
		this.notifyGameWasBeStarted();
	}

	@Override
	public int getBoardRows() {
		return this.board.getRows();
	}

	@Override
	public int getBoardCols() {
		return this.board.getCols();
	}

	@Override
	public String getBoardElementAt(int rowIndex, int columnIndex) {
		ComponentInterface nenufar = this.board.getElementAtCell(rowIndex, columnIndex);
		return (nenufar != null) ? nenufar.getImagePath() : "";
	}

	@Override
	public void attach(GameControllerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public String getGameTitle() {
		return "Player 1 x Player 2";
	}

	@Override
	public int getRedNenufarRowCount() {
		return 1;
	}

	@Override
	public int getRedNenufarColumnCount() {
		return 3;
	}

	@Override
	public String getRedNenufarAt(int rowIndex, int columnIndex) {
		ComponentInterface nenufar = null;
		if(columnIndex >= 0 && columnIndex < this.redFlowers.size()) {
			nenufar = this.redFlowers.get(columnIndex);
		}
		return (nenufar != null) ? nenufar.getImagePath() : "";
	}

	@Override
	public int getYellowNenufarRowCount() {
		return 1;
	}

	@Override
	public int getYellowNenufarColumnCount() {
		return 3;
	}

	@Override
	public String getYellowNenufarAt(int rowIndex, int columnIndex) {
		ComponentInterface nenufar = null;
		if(columnIndex >= 0 && columnIndex < this.yellowFlowers.size()) {
			nenufar = this.yellowFlowers.get(columnIndex);
		}
		return (nenufar != null) ? nenufar.getImagePath() : "";
	}

	@Override
	public void selectRedFlower(int selectedRow, int selectedColumn) {
		if(selectedColumn >= 0 && selectedColumn < this.redFlowers.size()) {
			Flower flower = this.redFlowers.remove(selectedColumn);
			this.notifyRedFlowerWasBeSelected(flower.getNumber());
		}
	}

	@Override
	public void selectYellowFlower(int selectedRow, int selectedColumn) {
		if(selectedColumn >= 0 && selectedColumn < this.yellowFlowers.size()) {
			Flower flower = this.yellowFlowers.remove(selectedColumn);
			this.notifyYellowFlowerWasBeSelected(flower.getNumber());
		}
	}

	@Override
	public void selectSquare(int selectedRow, int selectedColumn) {
		this.defineDarkenedNenufar(selectedRow, selectedColumn);
	}

	private void defineDarkenedNenufar(int selectedRow, int selectedColumn) {
		ComponentInterface nenufar = this.board.getElementAtCell(selectedRow, selectedColumn);
		if(nenufar != null && nenufar.getClass() == Nenufar.class) {
			ComponentInterface darkenedNenufar = NenufarFactory.getInstance().create(NenufarType.DARKENED_NENUFAR);
			this.board.setElementAtCell(darkenedNenufar, selectedRow, selectedColumn);
			this.notifyUpdatedGameBoard();
		}
	}

	protected void notifyGameWasBeStarted() {
		for(GameControllerObserver observer : this.observers) {
			observer.gameWasBeStarted();
		}
	}

	private void notifyRedFlowerWasBeSelected(int number) {
		for(GameControllerObserver observer : this.observers) {
			observer.redFlowerWasBeSelected(number);
		}
	}

	private void notifyYellowFlowerWasBeSelected(int number) {
		for(GameControllerObserver observer : this.observers) {
			observer.yellowFlowerWasBeSelected(number);
		}
	}

	private void notifyUpdatedGameBoard() {
		for(GameControllerObserver observer : this.observers) {
			observer.updateGameBoard();
		}
	}
	
}
