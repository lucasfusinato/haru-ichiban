package game.model.strategy.square;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public interface CompareNenufarStrategy extends CompareSquareStrategy<Nenufar> {

	@Override
	boolean compare(Square<Nenufar> square);

}
