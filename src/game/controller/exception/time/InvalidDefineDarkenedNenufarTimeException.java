package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidDefineDarkenedNenufarTimeException extends InvalidMoveTimeException {

	public InvalidDefineDarkenedNenufarTimeException() {
		super("Não é permitido definir um nenúfar escuro nesse momento.");
	}
	
}
