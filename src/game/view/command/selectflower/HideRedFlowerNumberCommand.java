package game.view.command.selectflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class HideRedFlowerNumberCommand implements Command {

	private GameControllerInterface gameController;

	public HideRedFlowerNumberCommand(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void execute() {
		gameController.hideRedFlowerNumber();
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
