package game.controller.command.withdrawflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class RequestWithdrawRedFlowerCommand implements Command {

	private GameControllerInterface gameController;

	public RequestWithdrawRedFlowerCommand(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void execute() throws Exception {
		gameController.requestRedFlowerWithdraw();
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
