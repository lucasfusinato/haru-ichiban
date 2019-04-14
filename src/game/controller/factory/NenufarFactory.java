package game.controller.factory;

import game.model.DarkenedNenufar;
import game.model.Nenufar;
import game.model.ComponentInterface;
import game.model.NenufarType;
import game.model.RedFlowerNenufar;
import game.model.RedFrogNenufar;
import game.model.YellowFlowerNenufar;
import game.model.YellowFrogNenufar;

public class NenufarFactory {
	
	private static NenufarFactory instance;

	public synchronized static NenufarFactory getInstance() {
		if(instance == null) {
			instance = new NenufarFactory();
		}
		return instance;
	}
	
	private NenufarFactory() {
		
	}

	public ComponentInterface create(NenufarType nenufarType) {
		ComponentInterface nenufar = null;
		switch (nenufarType) {
			case DARKENED_NENUFAR:
				nenufar = new DarkenedNenufar();
				break;
				
			case RED_FROG_NENUFAR:
				nenufar = new RedFrogNenufar();
				break;
				
			case YELLOW_FROG_NENUFAR:
				nenufar = new YellowFrogNenufar();
				break;
				
			case RED_FLOWER_NENUFAR:
				nenufar = new RedFlowerNenufar();
				break;
				
			case YELLOW_FLOWER_NENUFAR:
				nenufar = new YellowFlowerNenufar();
				break;
			
			default:
				nenufar = new Nenufar();
				break;
		}
		return nenufar;
	}

}
