package game.view.command.selectflower;

import game.controller.GameControllerInterface;
import game.view.ViewUtils;
import utils.Command;

public class SelectYellowFlowerCommand implements Command {

	private GameControllerInterface gameController;
	private int index;

	public SelectYellowFlowerCommand(GameControllerInterface gameController, int index) {
		this.gameController = gameController;
		this.index = index;
	}

	@Override
	public void execute() {
		try {
			gameController.selectYellowFlower(index);
		} catch (Exception e) {
			ViewUtils.catchException(e);
		}
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
