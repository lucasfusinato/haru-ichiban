package controller;

import java.util.ArrayList;
import java.util.List;

import utils.Observable;

public abstract class AbstractGameController implements GameControllerInterface, Observable<GameControllerObserver> {

	protected List<GameControllerObserver> observers;
	
	public AbstractGameController() {
		init();
	}
	
	private void init() {
		observers = new ArrayList<>();;
	}

	@Override
	public void attach(GameControllerObserver observer) {
		observers.add(observer);
	}

	@Override
	public void detach(GameControllerObserver observer) {
		observers.remove(observer);
	}
	
}
