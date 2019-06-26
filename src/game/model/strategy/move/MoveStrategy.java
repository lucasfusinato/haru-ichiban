package game.model.strategy.move;

import game.model.Direction;

public interface MoveStrategy {

	int calculateNextRow(int i);
	
	int calculateNextColumn(int j);
	
	Direction getDirection();
	
}
