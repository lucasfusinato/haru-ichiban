package game.view.command;

import game.controller.GameControllerInterface;
import game.view.command.board.JuniorGardenerFlowerSquareCommand;
import game.view.command.board.JuniorGardenerHaruIchibanCommand;
import game.view.command.board.SeniorGardenerDarkenedNenufarCommand;
import game.view.command.board.SeniorGardenerFlowerSquareCommand;
import utils.Command;

public class BoardMouseCommandFactory {

	private GameControllerInterface gameController;

	public BoardMouseCommandFactory(GameControllerInterface gameController) {
		this.gameController = gameController;
	}

	public Command createClickBoardCommand(int rowIndex, int columnIndex) {
		switch(gameController.getTurnStatus()) {
			case JUNIOR_GARDENER_FLOWER_SQUARE:
				return new JuniorGardenerFlowerSquareCommand(gameController, rowIndex, columnIndex);
			case SENIOR_GARDENER_FLOWER_SQUARE:
				return new SeniorGardenerFlowerSquareCommand(gameController, rowIndex, columnIndex);
			case JUNIOR_GARDENER_HARU_ICHIBAN:
				return new JuniorGardenerHaruIchibanCommand(gameController, rowIndex, columnIndex);
			case SENIOR_GARDENER_DARKENED_NENUFAR:
				return new SeniorGardenerDarkenedNenufarCommand(gameController, rowIndex, columnIndex);
			default:
				return null;
		}
	}

	public Command createPressBoardCommand(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Command createReleaseBoardCommand(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Command createEnterBoardCommand(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Command createExitBoardCommand(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
