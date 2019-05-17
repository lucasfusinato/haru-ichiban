package game.view.command.board;

import core.view.MainUtils;
import game.controller.GameControllerInterface;
import utils.Command;

public class SeniorGardenerDarkenedNenufarCommand implements Command {

	private GameControllerInterface gameController;
	private int rowIndex;
	private int columnIndex;
	
	public SeniorGardenerDarkenedNenufarCommand(GameControllerInterface gameController, int rowIndex, int columnIndex) {
		this.gameController = gameController;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	@Override
	public void execute() {
		try {
			gameController.defineDarkenedNenufar(rowIndex, columnIndex);
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
