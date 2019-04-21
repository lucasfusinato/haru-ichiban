package game.view.board;

import javax.swing.table.AbstractTableModel;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameBoardModel extends AbstractTableModel {

	private GameControllerInterface gameController;

	public GameBoardModel(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public int getRowCount() {
		return this.gameController.getBoardRowCount();
	}

	@Override
	public int getColumnCount() {
		return this.gameController.getBoardColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.gameController.getBoardElementImageAt(rowIndex, columnIndex);
	}

}
