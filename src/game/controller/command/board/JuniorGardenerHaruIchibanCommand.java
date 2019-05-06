package game.controller.command.board;

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
	public void execute() throws Exception {
		gameController.executeHaruIchiban(rowIndex, columnIndex);
	}

	@Override
	public void undo() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() throws Exception {
		// TODO Auto-generated method stub

	}

}
