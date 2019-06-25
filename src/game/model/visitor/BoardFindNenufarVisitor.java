package game.model.visitor;

import game.model.board.Board;
import game.model.board.Square;
import game.model.nenufar.Nenufar;
import game.model.strategy.nenufar.CompareNenufarStrategy;

public class BoardFindNenufarVisitor implements BoardVisitor<Nenufar> {

	private Square<Nenufar> square;
	private CompareNenufarStrategy strategy;
	
	public BoardFindNenufarVisitor(CompareNenufarStrategy strategy) {
		this.square = null;
		this.strategy = strategy;
	}
	
	@Override
	public void visit(Board<Nenufar> board) {
		int rows = board.getRows();
		int cols = board.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Square<Nenufar> square = board.getSquare(i, j);
				if(strategy.compare(square)) {
					this.square = square;
					return;
				}
			}
		}
	}
	
	public Square<Nenufar> getSquare() {
		return square;
	}

}
