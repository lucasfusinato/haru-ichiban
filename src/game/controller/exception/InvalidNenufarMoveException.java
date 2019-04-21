package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidNenufarMoveException extends InvalidMoveException {

	public InvalidNenufarMoveException() {
		super("O jardineiro sênior deve mover sua flor à um nenufar não florescido.");
	}

}
