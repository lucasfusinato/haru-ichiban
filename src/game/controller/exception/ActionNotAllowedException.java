package game.controller.exception;

@SuppressWarnings("serial")
public class ActionNotAllowedException extends Exception {

	public ActionNotAllowedException(String message) {
		super("A��o n�o permitida: " + message);
	}
	
}
