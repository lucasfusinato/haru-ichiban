package game.model.decorator;

import game.model.board.Square;
import game.model.flower.Flower;
import game.model.gardener.Gardener;
import game.model.gardener.GardenerColor;
import game.model.nenufar.Nenufar;

public abstract class AbstractGardenerDecorator implements Gardener {

	private Gardener gardener;
	
	public AbstractGardenerDecorator(Gardener gardener) {
		this.gardener = gardener;
	}
	
	@Override
	public void florescer(Square<Nenufar> nenufar, Flower flower) {
		this.gardener.florescer(nenufar, flower);
	}
	
	@Override
	public String getName() {
		return this.gardener.getName();
	}
	
	@Override
	public GardenerColor getColor() {
		return this.gardener.getColor();
	}
	
}
