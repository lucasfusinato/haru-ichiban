package game.model.strategy.move;

import game.model.Direction;

public class HaruIchibanDownMoveStrategy implements HaruIchibanMoveStrategy {

	@Override
	public int calculateNextRow(int i) {
		return i - 1;
	}

	@Override
	public int calculateNextColumn(int j) {
		return j;
	}

	@Override
	public Direction getDirection() {
		return Direction.DOWN;
	}

}
