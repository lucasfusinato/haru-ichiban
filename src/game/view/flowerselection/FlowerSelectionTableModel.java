package game.view.flowerselection;

import javax.swing.table.AbstractTableModel;

import game.controller.GameControllerInterface;
import game.model.gardener.GardenerColor;

@SuppressWarnings("serial")
public class FlowerSelectionTableModel extends AbstractTableModel {

	private GameControllerInterface gameController;
	private GardenerColor gardenerColor;

	public FlowerSelectionTableModel(GameControllerInterface gameController, GardenerColor gardenerColor) {
		this.gameController = gameController;
		this.gardenerColor = gardenerColor;
	}

	@Override
	public int getRowCount() {
		return this.gameController.getAvailableFlowerQuantity(this.gardenerColor);
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.gameController.getFlowerImageAt(this.gardenerColor, rowIndex);
	}
	
}
