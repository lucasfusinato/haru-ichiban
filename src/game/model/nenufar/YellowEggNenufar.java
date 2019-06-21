package game.model.nenufar;

import game.model.Element;
import game.model.factory.AbstractGardenerFactory;
import game.model.factory.YellowGardenerFactory;

public class YellowEggNenufar extends EggNenufar {

	@Override
	protected AbstractGardenerFactory getGardenerFactory() {
		return YellowGardenerFactory.getInstance();
	}
	
	@Override
	protected Element createDefaultElement() {
		return YellowGardenerFactory.getInstance().createFrog();
	}
	
}
