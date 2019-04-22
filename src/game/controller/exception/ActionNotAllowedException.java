package game.controller.exception;

@SuppressWarnings("serial")
public class ActionNotAllowedException extends Exception {

	public ActionNotAllowedException(String message) {
		super("Ação não permitida: " + message);
	}
	
}
