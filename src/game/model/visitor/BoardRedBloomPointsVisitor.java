package game.model.visitor;

import game.model.strategy.nenufar.CompareRedFlowerNenufarStrategy;

public class BoardRedBloomPointsVisitor extends BoardBloomPointsVisitor {

	public BoardRedBloomPointsVisitor() {
		super(new CompareRedFlowerNenufarStrategy());
	}

}
