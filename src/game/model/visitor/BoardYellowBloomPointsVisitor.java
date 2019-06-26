package game.model.visitor;

import game.model.strategy.square.CompareYellowFlowerNenufarStrategy;

public class BoardYellowBloomPointsVisitor extends BoardBloomPointsVisitor {

	public BoardYellowBloomPointsVisitor() {
		super(new CompareYellowFlowerNenufarStrategy());
	}

}
