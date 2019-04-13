package controller.factory;

import model.DarkenedNenufar;
import model.Nenufar;
import model.NenufarInterface;
import model.NenufarType;
import model.RedFlowerNenufar;
import model.RedFrogNenufar;
import model.YellowFlowerNenufar;
import model.YellowFrogNenufar;

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

	public NenufarInterface create(NenufarType nenufarType) {
		NenufarInterface nenufar = null;
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
