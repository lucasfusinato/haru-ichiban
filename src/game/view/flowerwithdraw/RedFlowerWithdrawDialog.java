package game.view.flowerwithdraw;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.view.ViewUtils;
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
						int index = columnIndex * (rowIndex + 1);
						if(gameController.hasWithdrawRedFlowerAt(index)) {
							return ViewUtils.createImagePath(gameController.getWithdrawRedFlowerAt(index) + "-" + gameController.getWithdrawRedFlowerNumberAt(index));
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
						return gameController.getWithdrawRedFlowerQuantity();
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
