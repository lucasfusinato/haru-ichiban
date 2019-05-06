package game.controller.builder;

import utils.Director;

public class NenufarBoardDirector implements Director {

	private NenufarBoardBuilder builder;
	
	public NenufarBoardDirector(NenufarBoardBuilder builder) {
		this.builder = builder;
	}
	
	@Override
	public void construct() {
		builder.reset();
		builder.definePieces();
	}

}
