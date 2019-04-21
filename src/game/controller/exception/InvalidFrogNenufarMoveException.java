package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidFrogNenufarMoveException extends InvalidMoveException {

	public InvalidFrogNenufarMoveException() {
		super("O jardineiro sênior deve mover o sapo à um nenufar vazio.");
	}

}
