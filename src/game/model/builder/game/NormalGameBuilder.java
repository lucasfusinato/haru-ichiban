package game.model.builder.game;

import game.model.board.NormalNenufarBoard;
import game.model.builder.board.BoardBuilder;
import game.model.builder.board.NormalNenufarBoardBuilder;
import game.model.nenufar.Nenufar;

public class NormalGameBuilder extends NenufarGameBuilder {

	@Override
	protected int getBoardRows() {
		return NormalNenufarBoard.ROWS;
	}

	@Override
	protected int getBoardCols() {
		return NormalNenufarBoard.COLS;
	}

	@Override
	protected BoardBuilder<Nenufar> getBoardBuilder() {
		return new NormalNenufarBoardBuilder();
	}

}
