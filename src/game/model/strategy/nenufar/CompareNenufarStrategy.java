package game.model.strategy.nenufar;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public interface CompareNenufarStrategy {

	boolean compare(Square<Nenufar> square);

}
