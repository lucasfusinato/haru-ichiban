package game.view;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class RedFlowerSelectionBoardModel extends FlowerSelectionBoardModel {

	public RedFlowerSelectionBoardModel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public int getRowCount() {
		return this.gameController.getRedNenufarRowCount();
	}

	@Override
	public int getColumnCount() {
		return this.gameController.getRedNenufarColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.gameController.getRedNenufarAt(rowIndex, columnIndex);
	}

}
