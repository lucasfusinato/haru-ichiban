package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidMoveException extends Exception {

	public InvallidMoveException() {
		super("Movimento n�o permitido.");
	}
	
	public InvallidMoveException(String message) {
		super(message);
	}

}
