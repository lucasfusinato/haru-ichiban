package game.model.visitor;

import game.model.board.Square;

public interface SquareVisitor<E> {

	void visit(Square<E> square);

}
