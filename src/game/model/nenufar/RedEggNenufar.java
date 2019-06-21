package game.model.nenufar;

import game.model.Element;
import game.model.factory.AbstractGardenerFactory;
import game.model.factory.RedGardenerFactory;

public class RedEggNenufar extends EggNenufar {

	@Override
	protected AbstractGardenerFactory getGardenerFactory() {
		return RedGardenerFactory.getInstance();
	}
	
	@Override
	protected Element createDefaultElement() {
		return RedGardenerFactory.getInstance().createFrog();
	}
	
}
