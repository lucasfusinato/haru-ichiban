package game.model.factory;

import game.model.flower.Flower;
import game.model.flower.YellowFlower;
import game.model.gardener.Gardener;
import game.model.gardener.YellowGardener;

public class YellowGardenerFactory extends AbstractGardenerFactory {

	private static YellowGardenerFactory instance;
	
	public static synchronized YellowGardenerFactory getInstance() {
		if(instance == null) {
			instance = new YellowGardenerFactory();
		}
		return instance;
	}

	@Override
	public Gardener createGardener(String name) {
		return new YellowGardener(name);
	}

	@Override
	public Flower createFlower(int number) {
		return new YellowFlower(number);
	}
	
}
