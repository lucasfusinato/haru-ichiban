package game.model.builder.game;

import game.model.board.AdvancedNenufarBoard;
import game.model.builder.board.AdvancedNenufarBoardBuilder;
import game.model.builder.board.BoardBuilder;
import game.model.nenufar.Nenufar;

public class AdvancedGameBuilder extends NenufarGameBuilder {

	@Override
	protected int getBoardRows() {
		return AdvancedNenufarBoard.ROWS;
	}

	@Override
	protected int getBoardCols() {
		return AdvancedNenufarBoard.COLS;
	}

	@Override
	protected BoardBuilder<Nenufar> getBoardBuilder() {
		return new AdvancedNenufarBoardBuilder();
	}

}
