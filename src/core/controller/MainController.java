package core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.controller.command.AboutCommand;
import core.controller.command.ExitCommand;
import core.controller.command.NewGameCommand;
import utils.Command;

public class MainController implements MainControllerInterface {
	
	private static MainController instance;
	private List<MainControllerObserver> observers;
	private HashMap<MainMenuOption, Command> mainMenuCommands;
	
	public static MainControllerInterface getInstance() {
		if(instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	private MainController() {
		this.init();
	}

	private void init() {
		this.observers = new ArrayList<>();
		this.mainMenuCommands = new HashMap<>();
		this.defineMainMenuCommands();
	}

	private void defineMainMenuCommands() {
		this.mainMenuCommands.put(MainMenuOption.NEW_GAME, 	new NewGameCommand(this));
		this.mainMenuCommands.put(MainMenuOption.ABOUT, 	new AboutCommand(this));
		this.mainMenuCommands.put(MainMenuOption.EXIT, 		new ExitCommand(this));
	}

	@Override
	public void attach(MainControllerObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void selectMenuOption(MainMenuOption mainMenuOption) {
		Command mainMenuCommand = this.mainMenuCommands.get(mainMenuOption);
		if(mainMenuCommand != null) {
			mainMenuCommand.execute();
		}
	}

	@Override
	public void exitSystem() {
		this.notifySystemWillBeClosed();
		System.exit(0);
	}

	@Override
	public void showSystemInformation() {
		this.notifyShowSystemInformation();
	}

	@Override
	public void startNewGame() {
		this.notifyGameWasBeStarted();
	}

	protected void notifySystemWillBeClosed() {
		for(MainControllerObserver observer : this.observers) {
			observer.systemWillBeClosed();
		}
	}

	protected void notifyShowSystemInformation() {
		for(MainControllerObserver observer : this.observers) {
			observer.showSystemInformation();
		}
	}

	protected void notifyGameWasBeStarted() {
		for(MainControllerObserver observer : this.observers) {
			observer.gameWasBeStarted();
		}
	}

	@Override
	public String getSystemTitle() {
		return "Haru Ichiban";
	}

}
