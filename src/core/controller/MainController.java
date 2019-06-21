package core.controller;

import java.util.ArrayList;
import java.util.List;

import game.controller.GameController;
import game.controller.GameControllerInterface;
import game.model.GameType;

public class MainController implements MainControllerInterface {
	
	private static MainController instance;
	private List<MainControllerObserver> observers;
	private String player1;
	private String player2;
	private Integer level;
	
	public static MainControllerInterface getInstance() {
		if(instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	private MainController() {
		observers = new ArrayList<>();
	}

	@Override
	public void attach(MainControllerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void detach(MainControllerObserver observer) {
		this.observers.remove(observer);
	}
	
	@Override
	public void startNewGame() {
		definePlayer1(null);
		definePlayer2(null);
		defineLevel(0);
		requestGameInformation();
	}

	@Override
	public void startGame() throws Exception {
		GameControllerInterface gameController = new GameController();
		gameController.startGame(player1, player2, level);
		notifyGameStarted(gameController);
	}

	@Override
	public void exitSystem() {
		notifySystemWillBeClosed();
		System.exit(0);
	}

	@Override
	public void definePlayer1(String playerName) {
		this.player1 = playerName;
		notifyUpdatedPlayer1();
	}

	@Override
	public void definePlayer2(String playerName) {
		this.player2 = playerName;
		notifyUpdatedPlayer2();
	}

	@Override
	public void defineLevel(Integer level) {
		this.level = level;
		notifyUpdatedLevel();
	}

	@Override
	public void showGameInformation() {
		notifyShowGameInformation();
	}

	@Override
	public String getSystemTitle() {
		return "Haru Ichiban";
	}

	@Override
	public String getSystemInformation() {
		return "Desenvolvido por Lucas Zanis e João Arruda.";
	}

	@Override
	public List<String> getLevelOptions() {
		List<String> options = new ArrayList<>();
		for(GameType type : GameType.values()) {
			options.add(type.getDescription());
		}
		return options;
	}

	protected void notifyUpdatedPlayer1() {
		for(MainControllerObserver observer : this.observers) {
			observer.updatePlayer1(player1);
		}
	}

	protected void notifyUpdatedPlayer2() {
		for(MainControllerObserver observer : this.observers) {
			observer.updatePlayer2(player2);
		}
	}

	protected void notifyUpdatedLevel() {
		for(MainControllerObserver observer : this.observers) {
			observer.updateLevel(level);
		}
	}

	protected void requestGameInformation() {
		for(MainControllerObserver observer : this.observers) {
			observer.requestGameInformation();
		}
	}

	private void notifyShowGameInformation() {
		String gameInformation = getSystemInformation();
		for(MainControllerObserver observer : this.observers) {
			observer.showGameInformation(gameInformation);
		}
	}

	protected void notifySystemWillBeClosed() {
		for(MainControllerObserver observer : this.observers) {
			observer.systemWillBeClosed();
		}
	}

	protected void notifyGameStarted(GameControllerInterface gameController) {
		for(MainControllerObserver observer : this.observers) {
			observer.gameStarted(gameController);
		}
	}

}
