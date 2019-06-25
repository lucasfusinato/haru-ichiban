package game.model.visitor;

import game.model.board.Board;
import game.model.nenufar.Nenufar;

public class ResetBoardElementVisitor implements BoardVisitor<Nenufar> {

	@Override
	public void visit(Board<Nenufar> board) {
		int rows = board.getRows();
		int cols = board.getCols();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Nenufar element = board.getElementAtSquare(i, j);
				if(element != null) {
					element.resetElement();
				}
			}
		}
	}

}
