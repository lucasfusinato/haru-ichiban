package game.model.factory;

import game.model.flower.Flower;
import game.model.flower.RedFlower;
import game.model.frog.Frog;
import game.model.frog.RedFrog;
import game.model.gardener.Gardener;
import game.model.gardener.RedGardener;

public class RedGardenerFactory extends AbstractGardenerFactory {

	private static RedGardenerFactory instance;
	
	public static synchronized RedGardenerFactory getInstance() {
		if(instance == null) {
			instance = new RedGardenerFactory();
		}
		return instance;
	}

	@Override
	public Gardener createGardener(String name) {
		return new RedGardener(name);
	}

	@Override
	public Flower createFlower(int number) {
		return new RedFlower(number);
	}

	@Override
	public Frog createFrog() {
		return new RedFrog();
	}
	
}
