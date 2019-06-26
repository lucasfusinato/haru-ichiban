package game.model.strategy.square;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class CompareNonFloweredNenufarStrategy implements CompareNenufarStrategy {

	@Override
	public boolean compare(Square<Nenufar> square) {
		return !(new CompareFlowerNenufarStrategy()).compare(square);
	}

}
