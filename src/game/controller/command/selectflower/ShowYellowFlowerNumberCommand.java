package game.controller.command.selectflower;

import game.controller.GameControllerInterface;
import utils.Command;

public class ShowYellowFlowerNumberCommand implements Command {

	private GameControllerInterface gameController;
	private int index;

	public ShowYellowFlowerNumberCommand(GameControllerInterface gameController, int index) {
		this.gameController = gameController;
		this.index = index;
	}

	@Override
	public void execute() throws Exception {
		gameController.showYellowFlowerNumber(index);
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
