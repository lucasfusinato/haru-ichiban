package game.view.flowerselection;

import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.controller.command.FlowerMouseCommandFactory;
import game.controller.command.YellowFlowerMouseCommandFactory;

@SuppressWarnings("serial")
public class YellowFlowerSelectionTable extends AbstractFlowerSelectionTable {

	public YellowFlowerSelectionTable(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected TableModel createTableModel() {
		return new YellowFlowerSelectionTableModel(getGameController());
	}

	@Override
	protected FlowerMouseCommandFactory createMouseCommandFactory() {
		return new YellowFlowerMouseCommandFactory(getGameController());
	}

}
