package game.model.strategy.square;

import game.model.Element;
import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class CompareNenufarElementStrategy implements CompareNenufarStrategy {

	private Class<? extends Element> target;

	public CompareNenufarElementStrategy(Class<? extends Element> target) {
		this.target = target;
	}

	@Override
	public boolean compare(Square<Nenufar> square) {
		if(square == null) {
			return false;
		}
		Nenufar nenufar = square.getElement();
		if(nenufar == null) {
			return false;
		}
		Element element = nenufar.getElement();
		if(element == null) {
			return false;
		}
		return element.getClass() == target;
	}

}
