package game.view.board;

import javax.swing.table.AbstractTableModel;

import game.controller.GameControllerInterface;
import game.view.ViewUtils;

@SuppressWarnings("serial")
public class GameBoardModel extends AbstractTableModel {

	private GameControllerInterface gameController;

	public GameBoardModel(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public int getRowCount() {
		return gameController.getBoardRowCount();
	}

	@Override
	public int getColumnCount() {
		return gameController.getBoardColumnCount();
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		String value = null;
		if(gameController.hasBoardElementAt(rowIndex, columnIndex)) {
			value = ViewUtils.createImagePath(gameController.getBoardElementAt(rowIndex, columnIndex));
		} else if(gameController.hasBoardInfoAt(rowIndex, columnIndex)) {
			value = ViewUtils.createImagePath(gameController.getBoardInfoAt(rowIndex, columnIndex));
		}
		return value;
	}

}
