package game.model.visitor;

import game.model.board.Board;
import game.model.board.Square;
import game.model.nenufar.Nenufar;
import game.model.strategy.square.CompareNenufarElementStrategy;

public class RemoveNenufarElementVisitor implements BoardVisitor<Nenufar> {

	private CompareNenufarElementStrategy strategy;

	public RemoveNenufarElementVisitor(CompareNenufarElementStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void visit(Board<Nenufar> board) {
		Square<Nenufar> square;
		int rows = board.getRows();
		int cols = board.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				square = board.getSquare(i, j);
				if(strategy.compare(square)) {
					square.getElement().setElement(null);
				}
			}
		}
	}

}
