package game.view;

import javax.swing.table.AbstractTableModel;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public abstract class FlowerSelectionBoardModel extends AbstractTableModel {

	protected GameControllerInterface gameController;
	
	public FlowerSelectionBoardModel(GameControllerInterface gameController) {
		this.init(gameController);
	}

	private void init(GameControllerInterface gameController) {
		this.gameController = gameController;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
