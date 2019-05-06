package game.model.factory;

import game.model.nenufar.DarkenedNenufar;
import game.model.nenufar.LightedNenufar;
import game.model.nenufar.RedFrogNenufar;
import game.model.nenufar.YellowFrogNenufar;

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

	public YellowFrogNenufar createYellowFrogNenufar() {
		return new YellowFrogNenufar();
	}

	public RedFrogNenufar createRedFrogNenufar() {
		return new RedFrogNenufar();
	}

}
