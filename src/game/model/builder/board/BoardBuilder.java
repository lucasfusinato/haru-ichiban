package game.model.builder.board;

import game.model.board.Board;

public abstract class BoardBuilder<T> {
	
	protected Board<T> board;
	
	public abstract void reset();
	
	public abstract void constructStartPosition();

	public Board<T> getBoard() {
		return board;
	}

}
