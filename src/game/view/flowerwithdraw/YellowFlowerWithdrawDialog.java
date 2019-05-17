package game.view.flowerwithdraw;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import core.view.MainUtils;
import game.controller.GameControllerInterface;
import game.view.command.FlowerMouseCommandFactory;
import game.view.command.withdrawflower.WithdrawYellowFlowerMouseCommandFactory;
import game.view.flowerselection.AbstractFlowerSelectionTable;

@SuppressWarnings("serial")
public class YellowFlowerWithdrawDialog extends AbstractFlowerWithdrawDialog {

	public YellowFlowerWithdrawDialog(GameControllerInterface gameController) {
		super(gameController);
		setTitle("Saque de flor amarela");
	}

	@Override
	protected JTable createFlowersTable() {
		return new AbstractFlowerSelectionTable(gameController, false) {
			@Override
			protected TableModel createTableModel() {
				return new AbstractTableModel() {
					@Override
					public String getValueAt(int rowIndex, int columnIndex) {
						int index = YellowFlowerWithdrawDialog.this.getRowIndex(rowIndex, columnIndex);
						if(gameController.hasWithdrawYellowFlowerAt(index)) {
							return MainUtils.createImagePath(gameController.getWithdrawYellowFlowerAt(index) + "-" + gameController.getWithdrawYellowFlowerNumberAt(index));
						} else {
							return null;
						}
					}
					@Override
					public int getRowCount() {
						return YellowFlowerWithdrawDialog.this.getRowCount();
					}
					@Override
					public int getColumnCount() {
						return YellowFlowerWithdrawDialog.this.getColumnCount();
					}
				};
			}
			@Override
			protected FlowerMouseCommandFactory createMouseCommandFactory() {
				return new WithdrawYellowFlowerMouseCommandFactory(gameController);
			}
		};
	}

}
