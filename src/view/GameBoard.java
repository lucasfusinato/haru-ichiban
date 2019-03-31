package view;

import java.awt.Color;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class GameBoard extends JTable {

	public GameBoard(GameBoardModel boardModel, int cellSize) {
		init(boardModel, cellSize);
	}
	
	private void init(GameBoardModel boardModel, int cellSize) {
		setRowHeight(cellSize);
		setGridColor(getCustomGridColor());
		setBackground(getCustomBackgroundColor());
		setModel(boardModel);
	}
	
	protected Color getCustomGridColor() {
		return new Color(170, 227, 250);
	}
	
	protected Color getCustomBackgroundColor() {
		return new Color(136, 207, 236);
	}
	
}
