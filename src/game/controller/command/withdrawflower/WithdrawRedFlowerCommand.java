package game.controller.command.withdrawflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class WithdrawRedFlowerCommand implements Command {

	private GameControllerInterface gameController;
	private int index;

	public WithdrawRedFlowerCommand(GameControllerInterface gameController, int index) {
		this.gameController = gameController;
		this.index = index;
	}

	@Override
	public void execute() throws Exception {
		gameController.withdrawRedFlower(index);
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
