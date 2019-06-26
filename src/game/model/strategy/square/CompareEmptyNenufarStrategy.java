package game.model.strategy.square;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class CompareEmptyNenufarStrategy implements CompareNenufarStrategy {

	@Override
	public boolean compare(Square<Nenufar> square) {
		if(square == null) {
			return false;
		}
		Nenufar nenufar = square.getElement();
		if(nenufar == null) {
			return false;
		}
		return nenufar.getElement() == null;
	}
	

}
