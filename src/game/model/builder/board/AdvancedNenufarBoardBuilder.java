package game.model.builder.board;

import game.model.board.AdvancedNenufarBoard;

public class AdvancedNenufarBoardBuilder extends NenufarBoardBuilder {

	@Override
	public void reset() {
		board = new AdvancedNenufarBoard();
	}

}
