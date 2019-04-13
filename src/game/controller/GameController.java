package game.controller;

import java.util.ArrayList;
import java.util.List;

import game.controller.factory.NenufarBoardFactory;
import game.model.NenufarBoard;
import game.model.NenufarBoardType;
import game.model.NenufarInterface;

public class GameController implements GameControllerInterface {

	private static GameController instance;
	private List<GameControllerObserver> observers;
	private NenufarBoard board;
	
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
		this.observers = new ArrayList<>();
		this.board = NenufarBoardFactory.getInstance().create(NenufarBoardType.EMPTY);
	}

	@Override
	public void startGame() {
		this.board = NenufarBoardFactory.getInstance().create(NenufarBoardType.START);
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
		NenufarInterface nenufar = this.board.getElementAtCell(rowIndex, columnIndex);
		return (nenufar != null) ? nenufar.getImagePath() : "";
	}

	protected void notifyGameWasBeStarted() {
		for(GameControllerObserver observer : this.observers) {
			observer.gameWasBeStarted();
		}
	}

	@Override
	public void attach(GameControllerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public String getGameTitle() {
		return "Player 1 x Player 2";
	}
	
}
