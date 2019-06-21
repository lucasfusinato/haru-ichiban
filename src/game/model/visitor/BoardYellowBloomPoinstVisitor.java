package game.model.visitor;

import game.model.flower.YellowFlower;

public class BoardYellowBloomPoinstVisitor extends BoardBloomPointsVisitor {

	public BoardYellowBloomPoinstVisitor() {
		super(YellowFlower.class);
	}

}
