package game.model.strategy.square;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class CompareNenufarStrategyImpl implements CompareNenufarStrategy {

	@Override
	public boolean compare(Square<Nenufar> square) {
		return square != null && square.getElement() != null;
	}

}
