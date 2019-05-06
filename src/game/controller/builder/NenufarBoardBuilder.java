package game.controller.builder;

import game.model.NenufarBoard;
import utils.Builder;

public abstract class NenufarBoardBuilder implements Builder<NenufarBoard> {

	private NenufarBoard object;
	
	@Override
	public void reset() {
		object = new NenufarBoard();
	}

	@Override
	public NenufarBoard getObject() {
		return object;
	}
	
	public abstract void definePieces();

}
