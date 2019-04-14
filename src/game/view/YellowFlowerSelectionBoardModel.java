package game.view;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class YellowFlowerSelectionBoardModel extends FlowerSelectionBoardModel {

	public YellowFlowerSelectionBoardModel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public int getRowCount() {
		return this.gameController.getYellowNenufarRowCount();
	}

	@Override
	public int getColumnCount() {
		return this.gameController.getYellowNenufarColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.gameController.getYellowNenufarAt(rowIndex, columnIndex);
	}

}
