package game.view.flowerselection;

import javax.swing.table.AbstractTableModel;

import core.view.MainUtils;
import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class RedFlowerSelectionTableModel extends AbstractTableModel {

	protected GameControllerInterface gameController;

	public RedFlowerSelectionTableModel(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public int getRowCount() {
		return gameController.getAvailableRedFlowerQuantity();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		String value = null;
		if(gameController.hasRedFlowerElementAt(rowIndex)) {
			value = gameController.getRedFlowerElementAt(rowIndex);
			if(gameController.isVisibleRedFlowerNumber(rowIndex)) {
				value += "-" + gameController.getVisibleRedFlowerNumber();
			} else if(gameController.hasSelectedRedFlower() && !gameController.isSelectedRedFlower(rowIndex)) {
				value = "lighted-" + value;
			}
			value = MainUtils.createImagePath(value);
		} else if(gameController.canWithdrawRedFlowerAt(rowIndex)) {
			value = MainUtils.createImagePath("add-flower");
		}
		return value;
	}
	
}
