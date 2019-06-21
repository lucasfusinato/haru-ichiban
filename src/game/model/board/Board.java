package game.model.board;

import game.model.visitor.BoardVisitor;

public class Board<E> {
	
	private final int ROWS;
	private final int COLS;
	private Square<E>[][] cells;
	
	public Board(int rows, int cols) {
		this.ROWS = rows;
		this.COLS = cols;
		this.init(rows, cols);
	}
	
	@SuppressWarnings("unchecked")
	private void init(int rows, int cols) {
		this.cells = new Square[ROWS][COLS];
		for(int i = 0; i < this.cells.length; i++) {
			for(int j = 0; j < this.cells[i].length; j++) {
				this.cells[i][j] = new Square<>(i, j);
			}	
		}
	}

	public boolean isOutOfBounds(int x, int y) {
		return x < 0 || y < 0 || x >= ROWS || y >= COLS;
	}

	public Square<E> getSquare(int rowIndex, int columnIndex) {
		return this.cells[rowIndex][columnIndex];
	}

	public E getElementAtSquare(int rowIndex, int columnIndex) {
		return this.getSquare(rowIndex, columnIndex).getElement();
	}

	public E getElementAtSquare(Square<E> square) {
		return square.getElement();
	}

	public void setElementAtSquare(E element, int rowIndex, int columnIndex) {
		this.getSquare(rowIndex, columnIndex).setElement(element);
	}

	public void setElementAtSquare(E element, Square<E> square) {
		square.setElement(element);
	}
	
	public final int getRows() {
		return this.ROWS;
	}
	
	public final int getCols() {
		return this.COLS;
	}
	
	public void accept(BoardVisitor<E> visitor) {
		visitor.visit(this);
	}
	
}
