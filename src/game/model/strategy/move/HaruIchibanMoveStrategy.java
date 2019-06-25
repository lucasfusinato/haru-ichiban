package game.model.strategy.move;

import game.model.Direction;

public interface HaruIchibanMoveStrategy {

	int calculateNextRow(int i);
	
	int calculateNextColumn(int j);
	
	Direction getDirection();
	
}
