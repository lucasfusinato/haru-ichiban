package game.view.flowerselection;

import javax.swing.table.AbstractTableModel;

import game.controller.GameControllerInterface;
import game.view.ViewUtils;

@SuppressWarnings("serial")
public class YellowFlowerSelectionTableModel extends AbstractTableModel {

	protected GameControllerInterface gameController;

	public YellowFlowerSelectionTableModel(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public int getRowCount() {
		return gameController.getAvailableYellowFlowerQuantity();
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		String value = null;
		if(gameController.hasYellowFlowerElementAt(rowIndex)) {
			value = gameController.getYellowFlowerElementAt(rowIndex);
			if(gameController.isVisibleYellowFlowerNumber(rowIndex)) {
				value += "-" + gameController.getVisibleYellowFlowerNumber();
			} else if(gameController.hasSelectedYellowFlower() && !gameController.isSelectedYellowFlower(rowIndex)) {
				value = "lighted-" + value;
			}
			value = ViewUtils.createImagePath(value);
		} else if(gameController.canWithdrawYellowFlowerAt(rowIndex)) {
			value = ViewUtils.createImagePath("add-flower");
		}
		return value;
	}
	
}
