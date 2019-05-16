package game.model.board;

import game.model.nenufar.Nenufar;

public class NormalNenufarBoard extends Board<Nenufar> {

	public final static int ROWS = 5;
	public final static int COLS = 5;
	
	public NormalNenufarBoard() {
		super(ROWS, COLS);
	}

}
