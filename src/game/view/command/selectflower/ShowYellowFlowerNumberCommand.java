package game.view.command.selectflower;

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
	public void execute() {
		gameController.showYellowFlowerNumber(index);
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
