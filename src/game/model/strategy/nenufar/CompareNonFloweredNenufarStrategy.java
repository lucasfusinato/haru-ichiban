package game.model.strategy.nenufar;

import game.model.board.Square;
import game.model.nenufar.Nenufar;

public class CompareNonFloweredNenufarStrategy implements CompareNenufarStrategy {

	private CompareNenufarStrategy nenufarStrategy = new CompareNenufarStrategyImpl();
	private CompareNenufarStrategy redFlowerStrategy = new CompareRedFlowerNenufarStrategy();
	private CompareNenufarStrategy yellowFlowerStrategy = new CompareYellowFlowerNenufarStrategy();
	
	@Override
	public boolean compare(Square<Nenufar> square) {
		return nenufarStrategy.compare(square) && !redFlowerStrategy.compare(square) && !yellowFlowerStrategy.compare(square);
	}

}
