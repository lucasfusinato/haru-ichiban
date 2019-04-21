package game.model.factory;

import game.model.flower.Flower;
import game.model.flower.RedFlower;
import game.model.flower.YellowFlower;
import game.model.gardener.GardenerColor;

public class FlowerFactory {

	private static FlowerFactory instance;
	
	public static synchronized FlowerFactory getInstance() {
		if(instance == null) {
			instance = new FlowerFactory();
		}
		return instance;
	}

	public Flower create(GardenerColor gardenerColor, int number) {
		Flower flower = null;
		switch (gardenerColor) {
			case RED:
				flower = this.createRedFlower(number);
				break;
			case YELLOW:
				flower = this.createYellowFlower(number);
				break;
			}
		return flower;
	}

	private RedFlower createRedFlower(int number) {
		return new RedFlower(number);
	}

	private YellowFlower createYellowFlower(int number) {
		return new YellowFlower(number);
	}
	

}
