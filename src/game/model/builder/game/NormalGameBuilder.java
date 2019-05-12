package game.model.builder.game;

import game.model.board.NormalNenufarBoard;

public class NormalGameBuilder extends NenufarGameBuilder {

	@Override
	public void constructBoard() {
		getGame().setBoard(new NormalNenufarBoard());
	}

	@Override
	protected int getBoardRows() {
		return NormalNenufarBoard.ROWS;
	}

	@Override
	protected int getBoardCols() {
		return NormalNenufarBoard.COLS;
	}

}
