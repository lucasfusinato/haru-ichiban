package game.view.board;

import javax.swing.JTable;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameBoardPanel extends AbstractGameBoardPanel {

	public GameBoardPanel(GameControllerInterface gameController) {
		super(gameController);
	}

	@Override
	protected JTable createGameBoard() {
		return new GameBoard(this.getGameController());
	}

	@Override
	protected String getBackgroundImagePath() {
		return "images/board-background.png";
	}

}
