package game.view.command;

import game.controller.GameControllerInterface;
import game.view.command.selectflower.HideYellowFlowerNumberCommand;
import game.view.command.selectflower.SelectYellowFlowerCommand;
import game.view.command.selectflower.ShowYellowFlowerNumberCommand;
import game.view.command.withdrawflower.RequestWithdrawYellowFlowerCommand;
import utils.Command;

public class YellowFlowerMouseCommandFactory extends FlowerMouseCommandFactory {

	public YellowFlowerMouseCommandFactory(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	public Command createClickFlowerCommand(int index) {
		if(gameController.canSelectYellowFlower()) {
			return new SelectYellowFlowerCommand(gameController, index);
		} else if(gameController.canWithdrawYellowFlowerAt(index)) {
			return new RequestWithdrawYellowFlowerCommand(gameController);
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
		return new HideYellowFlowerNumberCommand(gameController);
	}

	@Override
	public Command createDragFlowerCommand(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command createMoveFlowerCommand(int index) {
		return new ShowYellowFlowerNumberCommand(gameController, index);
	}

}
