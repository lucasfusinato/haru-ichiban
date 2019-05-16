package game.view.command.withdrawflower;

import game.controller.GameControllerInterface;
import game.view.command.FlowerMouseCommandFactory;
import utils.Command;

public class WithdrawYellowFlowerMouseCommandFactory extends FlowerMouseCommandFactory {

	public WithdrawYellowFlowerMouseCommandFactory(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public Command createClickFlowerCommand(int index) {
		return new WithdrawYellowFlowerCommand(gameController, index);
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
