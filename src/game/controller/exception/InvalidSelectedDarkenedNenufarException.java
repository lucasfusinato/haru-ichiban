package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidSelectedDarkenedNenufarException extends InvalidMoveException {

	public InvalidSelectedDarkenedNenufarException() {
		super("O jardineiro s�nior escolher um nenufar vazio como novo nenufar esc�ro.");
	}

}
