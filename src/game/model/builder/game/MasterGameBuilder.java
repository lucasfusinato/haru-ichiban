package game.model.builder.game;

import game.model.board.MasterNenufarBoard;
import game.model.builder.board.BoardBuilder;
import game.model.builder.board.MasterNenufarBoardBuilder;
import game.model.nenufar.Nenufar;

public class MasterGameBuilder extends NenufarGameBuilder {

	@Override
	protected int getBoardRows() {
		return MasterNenufarBoard.ROWS;
	}

	@Override
	protected int getBoardCols() {
		return MasterNenufarBoard.COLS;
	}

	@Override
	protected BoardBuilder<Nenufar> getBoardBuilder() {
		return new MasterNenufarBoardBuilder();
	}

}
