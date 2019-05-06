package game.controller.command.board;

import game.controller.GameControllerInterface;
import utils.Command;

public class SeniorGardenerFlowerSquareCommand implements Command {

	private GameControllerInterface gameController;
	private int rowIndex;
	private int columnIndex;

	public SeniorGardenerFlowerSquareCommand(GameControllerInterface gameController, int rowIndex, int columnIndex) {
		this.gameController = gameController;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	@Override
	public void execute() throws Exception {
		gameController.defineSeniorGardenerFlowerSquare(rowIndex, columnIndex);
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
