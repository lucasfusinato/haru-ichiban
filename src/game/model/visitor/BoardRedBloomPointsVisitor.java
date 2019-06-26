package game.model.visitor;

import game.model.strategy.square.CompareRedFlowerNenufarStrategy;

public class BoardRedBloomPointsVisitor extends BoardBloomPointsVisitor {

	public BoardRedBloomPointsVisitor() {
		super(new CompareRedFlowerNenufarStrategy());
	}

}
