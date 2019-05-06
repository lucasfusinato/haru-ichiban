package game.controller.command.selectflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class ShowRedFlowerNumberCommand implements Command {

	private GameControllerInterface gameController;
	private int index;

	public ShowRedFlowerNumberCommand(GameControllerInterface gameController, int index) {
		this.gameController = gameController;
		this.index = index;
	}

	@Override
	public void execute() throws Exception {
		gameController.showRedFlowerNumber(index);
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
