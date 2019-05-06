package game.view.flowerwithdraw;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.controller.command.FlowerMouseCommandFactory;
import game.controller.command.withdrawflower.WithdrawYellowFlowerMouseCommandFactory;
import game.view.ViewUtils;
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
						if(gameController.hasWithdrawYellowFlowerAt(columnIndex)) {
							return ViewUtils.createImagePath(gameController.getWithdrawYellowFlowerAt(columnIndex) + "-" + gameController.getWithdrawYellowFlowerNumberAt(columnIndex));
						} else {
							return null;
						}
					}
					@Override
					public int getRowCount() {
						return 1;
					}
					@Override
					public int getColumnCount() {
						return gameController.getWithdrawYellowFlowerQuantity();
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
