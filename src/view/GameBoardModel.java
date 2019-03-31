package view;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import controller.GameControllerInterface;
import view.factory.ResizedImageIconFactory;

@SuppressWarnings("serial")
public class GameBoardModel extends AbstractTableModel {
	
	private GameControllerInterface gameController;
	private int spriteSize;
	
	public GameBoardModel(GameControllerInterface gameController, int cellSize) {
		this.gameController   = gameController;
		this.spriteSize = (int) (cellSize * 0.9);
	}

	@Override
	public int getRowCount() {
		return this.gameController.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return this.gameController.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ResizedImageIconFactory.getInstance().create(gameController.getValueAt(rowIndex, columnIndex), spriteSize, spriteSize);
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return ImageIcon.class;
	}

}
