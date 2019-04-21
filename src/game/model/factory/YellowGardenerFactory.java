package game.model.factory;

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
	public Gardener create() {
		return new YellowGardener("Jardineiro Amarelo");
	}

}
