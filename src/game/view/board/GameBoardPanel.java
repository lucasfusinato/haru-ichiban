package game.view.board;

import game.controller.GameControllerInterface;

@SuppressWarnings("serial")
public class GameBoardPanel extends AbstractGameBoardPanel {

	public GameBoardPanel(GameControllerInterface gameController) {
		super(gameController);
		setOpaque(false);
	}

	@Override
	protected GameBoard createGameBoard() {
		return new GameBoard(this.getGameController());
	}

}
