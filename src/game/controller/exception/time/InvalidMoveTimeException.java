package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidMoveTimeException extends Exception {

	public InvalidMoveTimeException() {
		super("Movimento não permitido nessa etapa do jogo.");
	}

	public InvalidMoveTimeException(String message) {
		super(message);
	}
	
}
