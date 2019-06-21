package game.model.visitor;

import game.model.board.Board;

public interface BoardVisitor<E> {
	
	void visit(Board<E> board);

}
