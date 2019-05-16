package game.view.command;

import game.controller.GameControllerInterface;
import utils.Command;

public abstract class FlowerMouseCommandFactory {
	
	protected GameControllerInterface gameController;

	public FlowerMouseCommandFactory(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	public abstract Command createClickFlowerCommand(int index);

	public abstract Command createPressFlowerCommand(int index);

	public abstract Command createReleaseFlowerCommand(int index);

	public abstract Command createEnterFlowerCommand(int index);

	public abstract Command createExitFlowerCommand(int index);

	public abstract Command createDragFlowerCommand(int index);

	public abstract Command createMoveFlowerCommand(int index);
	
}
