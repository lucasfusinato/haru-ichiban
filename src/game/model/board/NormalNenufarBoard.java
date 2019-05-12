package game.model;

import game.model.nenufar.Nenufar;

public class NenufarBoard extends Board<Nenufar> {

	private final static int ROWS = 5;
	private final static int COLS = 5;
	
	public NenufarBoard() {
		super(ROWS, COLS);
	}

}
