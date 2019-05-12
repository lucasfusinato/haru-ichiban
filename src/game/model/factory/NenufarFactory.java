package game.model.factory;

import game.model.nenufar.DarkenedNenufar;
import game.model.nenufar.LightedNenufar;

public class NenufarFactory {

	private static NenufarFactory instance;
	
	public static synchronized NenufarFactory getInstance() {
		if(instance == null) {
			instance = new NenufarFactory();
		}
		return instance;
	}

	public LightedNenufar createLightedNenufar() {
		return new LightedNenufar();
	}

	public DarkenedNenufar createDarkenedNenufar() {
		return new DarkenedNenufar();
	}

}
