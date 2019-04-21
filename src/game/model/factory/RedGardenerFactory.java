package game.model.factory;

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
	public Gardener create() {
		return new RedGardener("Jardineiro Vermelho");
	}

}
