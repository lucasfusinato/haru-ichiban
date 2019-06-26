package game.model.strategy.square;

import game.model.board.Square;

public interface CompareSquareStrategy<E> {

	boolean compare(Square<E> square);
	
}
