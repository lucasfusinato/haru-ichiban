package game.model.gardener;

import game.model.board.Square;
import game.model.flower.Flower;
import game.model.nenufar.Nenufar;

public interface Gardener {

	GardenerColor getColor();
	
	String getName();
	
	void florescer(Square<Nenufar> nenufar, Flower flower);
	
}
