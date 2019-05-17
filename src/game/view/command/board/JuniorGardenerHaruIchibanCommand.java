package game.view.command.board;

import core.view.MainUtils;
import game.controller.GameControllerInterface;
import utils.Command;

public class JuniorGardenerHaruIchibanCommand implements Command {

	private GameControllerInterface gameController;
	private int rowIndex;
	private int columnIndex;
	
	public JuniorGardenerHaruIchibanCommand(GameControllerInterface gameController, int rowIndex, int columnIndex) {
		this.gameController = gameController;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	@Override
	public void execute() {
		try {
			gameController.executeHaruIchiban(rowIndex, columnIndex);
		} catch (Exception e) {
			MainUtils.catchException(e);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

}
