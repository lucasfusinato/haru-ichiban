package game.model.visitor;

import game.model.flower.RedFlower;

public class BoardRedBloomPoinstVisitor extends BoardBloomPointsVisitor {

	public BoardRedBloomPoinstVisitor() {
		super(RedFlower.class);
	}

}
