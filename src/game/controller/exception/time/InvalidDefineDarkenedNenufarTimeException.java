package game.controller.exception.time;

@SuppressWarnings("serial")
public class InvalidDefineDarkenedNenufarTimeException extends InvalidMoveTimeException {

	public InvalidDefineDarkenedNenufarTimeException() {
		super("N�o � permitido definir um nen�far escuro nesse momento.");
	}
	
}
