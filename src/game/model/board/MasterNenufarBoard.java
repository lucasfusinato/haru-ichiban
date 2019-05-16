package game.model.board;

import game.model.nenufar.Nenufar;

public class MasterNenufarBoard extends Board<Nenufar> {

	public final static int ROWS = 9;
	public final static int COLS = 9;

	public MasterNenufarBoard() {
		super(ROWS, COLS);
	}

}
