package game.controller.command.withdrawflower;

import game.controller.GameControllerInterface;
import game.controller.command.FlowerMouseCommandFactory;
import utils.Command;

public class WithdrawRedFlowerMouseCommandFactory extends FlowerMouseCommandFactory {

	public WithdrawRedFlowerMouseCommandFactory(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public Command createClickFlowerCommand(int index) {
		return new WithdrawRedFlowerCommand(gameController, index);
	}

	@Override
	public Command createPressFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createReleaseFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createEnterFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createExitFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createDragFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createMoveFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
