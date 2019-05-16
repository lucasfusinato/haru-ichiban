package game.model.builder.board;

import game.model.board.NormalNenufarBoard;

public class NormalNenufarBoardBuilder extends NenufarBoardBuilder {

	@Override
	public void reset() {
		board = new NormalNenufarBoard();
	}

}
