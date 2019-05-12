package game.model.builder.game;

import game.model.board.MasterNenufarBoard;

public class MasterGameBuilder extends NenufarGameBuilder {

	@Override
	public void constructBoard() {
		getGame().setBoard(new MasterNenufarBoard());
	}

	@Override
	protected int getBoardRows() {
		return MasterNenufarBoard.ROWS;
	}

	@Override
	protected int getBoardCols() {
		return MasterNenufarBoard.COLS;
	}

}
