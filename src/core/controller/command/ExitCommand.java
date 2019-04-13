package core.controller.command;

import core.controller.MainControllerInterface;
import utils.Command;

public class ExitCommand implements Command {

	private MainControllerInterface mainController;

	public ExitCommand(MainControllerInterface mainController) {
		this.mainController = mainController;
	}

	@Override
	public void execute() {
		this.mainController.exitSystem();
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
