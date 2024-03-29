package game.view.flowerselection;

import javax.swing.table.TableModel;

import game.controller.GameControllerInterface;
import game.view.command.FlowerMouseCommandFactory;
import game.view.command.RedFlowerMouseCommandFactory;

@SuppressWarnings("serial")
public class RedFlowerSelectionTable extends AbstractFlowerSelectionTable {

	public RedFlowerSelectionTable(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected TableModel createTableModel() {
		return new RedFlowerSelectionTableModel(getGameController());
	}

	@Override
	protected FlowerMouseCommandFactory createMouseCommandFactory() {
		return new RedFlowerMouseCommandFactory(getGameController());
	}

}
