package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidFrogNenufarMoveException extends InvalidMoveException {

	public InvalidFrogNenufarMoveException() {
		super("O jardineiro s�nior deve mover o sapo � um nenufar vazio.");
	}

}
