package game.model.nenufar;

import game.model.factory.AbstractGardenerFactory;
import game.model.factory.RedGardenerFactory;

public class RedEggNenufar extends EggNenufar {

	@Override
	protected AbstractGardenerFactory getGardenerFactory() {
		return RedGardenerFactory.getInstance();
	}
	
}
