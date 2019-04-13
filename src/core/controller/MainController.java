package core.controller;

import java.util.ArrayList;
import java.util.List;

public class MainController implements MainControllerInterface {
	
	private static MainController instance;
	private List<MainControllerObserver> observers;
	
	public static MainControllerInterface getInstance() {
		if(instance == null) {
			instance = new MainController();
		}
		return instance;
	}
	
	private MainController() {
		this.observers = new ArrayList<>();
	}

	@Override
	public void attach(MainControllerObserver observer) {
		this.observers.add(observer);
	}

}
