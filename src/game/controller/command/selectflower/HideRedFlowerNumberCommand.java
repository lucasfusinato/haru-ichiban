package game.controller.command.selectflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class HideRedFlowerNumberCommand implements Command {

	private GameControllerInterface gameController;

	public HideRedFlowerNumberCommand(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void execute() throws Exception {
		gameController.hideRedFlowerNumber();
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
