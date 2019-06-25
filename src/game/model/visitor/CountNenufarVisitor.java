package game.model.visitor;

import game.model.board.Board;
import game.model.nenufar.Nenufar;
import game.model.strategy.nenufar.CompareNenufarStrategy;

public class CountNenufarVisitor implements BoardVisitor<Nenufar> {

	private int count;
	private CompareNenufarStrategy strategy;

	public CountNenufarVisitor(CompareNenufarStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void visit(Board<Nenufar> board) {
		int rows = board.getRows();
		int cols = board.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(strategy.compare(board.getSquare(i, j))) {
					count++;
				}
			}
		}
	}
	
	public int getCount() {
		return count;
	}

}
