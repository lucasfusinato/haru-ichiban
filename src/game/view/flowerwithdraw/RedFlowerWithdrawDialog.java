package game.view.flowerwithdraw;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import core.view.MainUtils;
import game.controller.GameControllerInterface;
import game.view.command.FlowerMouseCommandFactory;
import game.view.command.withdrawflower.WithdrawRedFlowerMouseCommandFactory;
import game.view.flowerselection.AbstractFlowerSelectionTable;

@SuppressWarnings("serial")
public class RedFlowerWithdrawDialog extends AbstractFlowerWithdrawDialog {

	public RedFlowerWithdrawDialog(GameControllerInterface gameController) {
		super(gameController);
		setTitle("Saque de flor vermelha");
	}

	@Override
	protected JTable createFlowersTable() {
		return new AbstractFlowerSelectionTable(gameController, false) {
			@Override
			protected TableModel createTableModel() {
				return new AbstractTableModel() {
					@Override
					public String getValueAt(int rowIndex, int columnIndex) {
						int index = RedFlowerWithdrawDialog.this.getRowIndex(rowIndex, columnIndex);
						if(gameController.hasWithdrawRedFlowerAt(index)) {
							return MainUtils.createImagePath(gameController.getWithdrawRedFlowerAt(index) + "-" + gameController.getWithdrawRedFlowerNumberAt(index));
						} else {
							return null;
						}
					}
					@Override
					public int getRowCount() {
						return RedFlowerWithdrawDialog.this.getRowCount();
					}
					@Override
					public int getColumnCount() {
						return RedFlowerWithdrawDialog.this.getColumnCount();
					}
				};
			}
			@Override
			protected FlowerMouseCommandFactory createMouseCommandFactory() {
				return new WithdrawRedFlowerMouseCommandFactory(gameController);
			}
		};
	}

}
