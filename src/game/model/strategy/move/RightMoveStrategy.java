package game.model.strategy.move;

import game.model.Direction;

public class RightMoveStrategy implements MoveStrategy {

	@Override
	public int calculateNextRow(int i) {
		return i;
	}

	@Override
	public int calculateNextColumn(int j) {
		return j - 1;
	}

	@Override
	public Direction getDirection() {
		return Direction.RIGHT;
	}

}
