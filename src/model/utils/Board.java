package model.utils;

public class Board<E> {
	
	public final int ROWS;
	public final int COLS;
	private Square<E>[][] cells;
	
	public Board(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		init(rows, cols);
	}
	
	@SuppressWarnings("unchecked")
	private void init(int rows, int cols) {
		cells = new Square[ROWS][COLS];
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Square<>();
			}	
		}
	}

	public Square<E> getCell(int rowIndex, int columnIndex) {
		return cells[rowIndex][columnIndex];
	}

	public E getElementAtCell(int rowIndex, int columnIndex) {
		return getCell(rowIndex, columnIndex).getElement();
	}
	
	public final int getRows() {
		return ROWS;
	}
	
	public final int getCols() {
		return COLS;
	}
	
}
