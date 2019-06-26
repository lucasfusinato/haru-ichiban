package game.model.factory;

import game.model.flower.Flower;
import game.model.flower.YellowFlower;
import game.model.frog.Frog;
import game.model.frog.YellowFrog;
import game.model.gardener.AbstractGardener;
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
	public AbstractGardener createGardener(String name) {
		return new YellowGardener(name);
	}

	@Override
	public Flower createFlower(int number) {
		return new YellowFlower(number);
	}

	@Override
	public Frog createFrog() {
		return new YellowFrog();
	}
	
}
