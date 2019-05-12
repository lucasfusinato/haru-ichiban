package game.model.gardener;

import game.model.Player;

public abstract class Gardener extends Player {
	
	public Gardener(String name) {
		super(name);
	}
	
	public abstract GardenerColor getColor();

}
