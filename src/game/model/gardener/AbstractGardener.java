package game.model.gardener;

import game.model.board.Square;
import game.model.flower.Flower;
import game.model.nenufar.Nenufar;

public abstract class AbstractGardener implements Gardener {

	private String name;
	
	public AbstractGardener(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void florescer(Square<Nenufar> nenufar, Flower flower) {
		nenufar.getElement().setElement(flower);
		nenufar.getElement().activeTopSide();
	}
	
}
