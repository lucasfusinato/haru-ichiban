package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {

	public InvalidMoveException() {
		super("Movimento n�o permitido.");
	}
	
	public InvalidMoveException(String message) {
		super(message);
	}

}
