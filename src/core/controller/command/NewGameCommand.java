package core.controller.command;

import core.controller.MainControllerInterface;
import utils.Command;

public class NewGameCommand implements Command {

	private MainControllerInterface mainController;
	
	public NewGameCommand(MainControllerInterface mainController) {
		this.mainController = mainController;
	}

	@Override
	public void execute() {
		this.mainController.startNewGame();
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
