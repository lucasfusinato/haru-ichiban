package game.model.strategy.square;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class CompareFlowerNenufarStrategy implements CompareNenufarStrategy {

	@Override
	public boolean compare(Square<Nenufar> square) {
		return (new CompareRedFlowerNenufarStrategy()).compare(square)
			|| (new CompareYellowFlowerNenufarStrategy()).compare(square);
	}

}
