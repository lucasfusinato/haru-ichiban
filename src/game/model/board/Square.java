package game.model.board;

import game.model.visitor.SquareVisitor;

public class Square<E> {
	
	private E element;
	private int x;
	private int y;
	private Board<E> board;

	public Square(Board<E> board, int x, int y) {
		this.board = board;
		this.x = x;
		this.y = y;
		this.element = null;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public int getRow() {
		return x;
	}

	public int getColumn() {
		return y;
	}

	public void accept(SquareVisitor<E> visitor) {
		visitor.visit(this);
	}

	public Board<E> getBoard() {
		return board;
	}
	
}
