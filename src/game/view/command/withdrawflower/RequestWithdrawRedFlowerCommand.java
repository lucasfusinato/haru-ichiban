package game.view.command.withdrawflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class RequestWithdrawRedFlowerCommand implements Command {

	private GameControllerInterface gameController;

	public RequestWithdrawRedFlowerCommand(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void execute() {
		gameController.requestRedFlowerWithdraw();
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
