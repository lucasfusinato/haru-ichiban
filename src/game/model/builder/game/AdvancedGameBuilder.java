package game.model.builder.game;

import game.model.board.AdvancedNenufarBoard;

public class AdvancedGameBuilder extends NenufarGameBuilder {

	@Override
	public void constructBoard() {
		getGame().setBoard(new AdvancedNenufarBoard());
	}

	@Override
	protected int getBoardRows() {
		return AdvancedNenufarBoard.ROWS;
	}

	@Override
	protected int getBoardCols() {
		return AdvancedNenufarBoard.COLS;
	}

}
