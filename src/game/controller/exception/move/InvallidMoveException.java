package game.controller.exception.move;

@SuppressWarnings("serial")
public class InvallidMoveException extends Exception {

	public InvallidMoveException() {
		super("Movimento não permitido.");
	}
	
	public InvallidMoveException(String message) {
		super(message);
	}

}
