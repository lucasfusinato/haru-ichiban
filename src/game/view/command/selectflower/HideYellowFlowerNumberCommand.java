package game.view.command.selectflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class HideYellowFlowerNumberCommand implements Command {

	private GameControllerInterface gameController;

	public HideYellowFlowerNumberCommand(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void execute() {
		gameController.hideYellowFlowerNumber();
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
