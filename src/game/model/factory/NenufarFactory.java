package game.model.factory;

import game.model.gardener.GardenerColor;
import game.model.nenufar.DarkenedNenufar;
import game.model.nenufar.Nenufar;
import game.model.nenufar.RedFlowerNenufar;
import game.model.nenufar.RedFrogNenufar;
import game.model.nenufar.StandardNenufar;
import game.model.nenufar.YellowFlowerNenufar;
import game.model.nenufar.YellowFrogNenufar;

public class NenufarFactory {

	private static NenufarFactory instance;
	
	public static synchronized NenufarFactory getInstance() {
		if(instance == null) {
			instance = new NenufarFactory();
		}
		return instance;
	}

	public StandardNenufar createNenufar() {
		return new StandardNenufar();
	}

	public YellowFrogNenufar createYellowFrogNenufar() {
		return new YellowFrogNenufar();
	}

	public RedFrogNenufar createRedFrogNenufar() {
		return new RedFrogNenufar();
	}

	public DarkenedNenufar createDarkenedNenufar() {
		return new DarkenedNenufar();
	}
	
	public Nenufar createFlowerNenufar(GardenerColor color) {
		Nenufar nenufar = null;
		switch(color) {
			case RED:
				nenufar = this.createRedFlowerNenufar();
				break;
			case YELLOW:
				nenufar = this.createYellowFlowerNenufar();
				break;
		}
		return nenufar;
	}
	
	public RedFlowerNenufar createRedFlowerNenufar() {
		return new RedFlowerNenufar();
	}
	
	public YellowFlowerNenufar createYellowFlowerNenufar() {
		return new YellowFlowerNenufar();
	}

}
