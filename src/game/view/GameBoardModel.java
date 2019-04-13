package game.view;

import javax.swing.table.AbstractTableModel;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameBoardModel extends AbstractTableModel {
	
	private GameControllerInterface gameController;
	
	public GameBoardModel(GameControllerInterface gameController) {
		this.init(gameController);
	}

	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public int getRowCount() {
		return this.gameController.getBoardRows();
	}

	@Override
	public int getColumnCount() {
		return this.gameController.getBoardCols();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.gameController.getBoardElementAt(rowIndex, columnIndex);
	}

}
