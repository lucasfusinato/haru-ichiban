package game.controller.command.withdrawflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class WithdrawYellowFlowerCommand implements Command {

	private GameControllerInterface gameController;
	private int index;

	public WithdrawYellowFlowerCommand(GameControllerInterface gameController, int index) {
		this.gameController = gameController;
		this.index = index;
	}

	@Override
	public void execute() throws Exception {
		gameController.withdrawYellowFlowerAt(index);
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
