package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidNenufarMoveException extends InvalidMoveException {

	public InvalidNenufarMoveException() {
		super("O jardineiro s�nior deve mover sua flor � um nenufar n�o florescido.");
	}

}
