package game.model;

import model.utils.Board;

public class NenufarBoard extends Board<ComponentInterface> {

	private final static int ROWS = 5;
	private final static int COLS = 5;
	
	public NenufarBoard() {
		super(ROWS, COLS);
	}

}
