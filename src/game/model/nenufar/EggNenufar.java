package game.model.nenufar;

import game.model.factory.AbstractGardenerFactory;

public abstract class EggNenufar extends LightedNenufar {

	public EggNenufar() {
		super();
		setElement(getGardenerFactory().createFrog());
	}

	protected abstract AbstractGardenerFactory getGardenerFactory();
	
}
