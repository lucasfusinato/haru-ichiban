package game.model.factory;

import game.model.flower.Flower;
import game.model.frog.Frog;
import game.model.gardener.Gardener;

public abstract class AbstractGardenerFactory {

	public abstract Gardener createGardener(String name);
	
	public abstract Flower createFlower(int number);

	public abstract Frog createFrog();
	
}
