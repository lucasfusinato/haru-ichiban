package game.controller.command;

import game.controller.GameControllerInterface;
import game.controller.command.selectflower.HideRedFlowerNumberCommand;
import game.controller.command.selectflower.SelectRedFlowerCommand;
import game.controller.command.selectflower.ShowRedFlowerNumberCommand;
import game.controller.command.withdrawflower.RequestWithdrawRedFlowerCommand;
import utils.Command;

public class RedFlowerMouseCommandFactory extends FlowerMouseCommandFactory {

	public RedFlowerMouseCommandFactory(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public Command createClickFlowerCommand(int index) {
		if(gameController.canSelectRedFlower()) {
			return new SelectRedFlowerCommand(gameController, index);
		} else if(gameController.canWithdrawRedFlowerAt(index)) {
			return new RequestWithdrawRedFlowerCommand(gameController);
		}
		return null;
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
		return new HideRedFlowerNumberCommand(gameController);
	}

	@Override
	public Command createDragFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createMoveFlowerCommand(int index) {
		return new ShowRedFlowerNumberCommand(gameController, index);
	}

}
