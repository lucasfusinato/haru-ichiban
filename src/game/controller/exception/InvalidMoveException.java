package game.controller.exception;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {

	public InvalidMoveException(String message) {
		super("Jogada inválida: " + message);
	}
	
}
