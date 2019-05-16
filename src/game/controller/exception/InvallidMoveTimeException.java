package game.controller.exception;

@SuppressWarnings("serial")
public class InvallidMoveTimeException extends Exception {

	public InvallidMoveTimeException() {
		super("Movimento n�o permitido nessa etapa do jogo.");
	}

	public InvallidMoveTimeException(String message) {
		super(message);
	}
	
}
