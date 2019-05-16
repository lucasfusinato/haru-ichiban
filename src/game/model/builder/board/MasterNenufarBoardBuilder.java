package game.model.builder.board;

import game.model.board.MasterNenufarBoard;

public class MasterNenufarBoardBuilder extends NenufarBoardBuilder {

	@Override
	public void reset() {
		board = new MasterNenufarBoard();
	}

}
