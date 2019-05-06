package game.controller.command.selectflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class HideYellowFlowerNumberCommand implements Command {

	private GameControllerInterface gameController;

	public HideYellowFlowerNumberCommand(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	@Override
	public void execute() throws Exception {
		gameController.hideYellowFlowerNumber();
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
