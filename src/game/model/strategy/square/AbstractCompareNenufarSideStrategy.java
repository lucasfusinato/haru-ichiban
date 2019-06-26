package game.model.strategy.square;

import game.model.board.Square;
import game.model.nenufar.Nenufar;
import game.model.nenufar.NenufarSide;

public abstract class AbstractCompareNenufarSideStrategy implements CompareNenufarStrategy {

	private NenufarSide nenufarSide;
	
	public AbstractCompareNenufarSideStrategy(NenufarSide nenufarSide) {
		this.nenufarSide = nenufarSide;
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
		return nenufar.getActiveSide() == this.nenufarSide;
	}

}
