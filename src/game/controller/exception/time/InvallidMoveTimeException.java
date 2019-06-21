package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvallidMoveTimeException extends Exception {

	public InvallidMoveTimeException() {
		super("Movimento não permitido nessa etapa do jogo.");
	}

	public InvallidMoveTimeException(String message) {
		super(message);
	}
	
}
